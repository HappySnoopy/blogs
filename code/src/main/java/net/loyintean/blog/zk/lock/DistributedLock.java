package net.loyintean.blog.zk.lock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * DistributedLock lock = null;
 * try {
 * lock = new DistributedLock("127.0.0.1:2182","test");
 * lock.lock();
 * //do something...
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * finally {
 * if(lock != null)
 * lock.unlock();
 * }
 *
 * @author xueliang
 */
public class DistributedLock implements Lock, Watcher {

    private ZooKeeper zk;
    private String root = "/locks";//根
    private String lockName;//竞争资源的标志
    private String waitNode;//等待前一个锁
    private String myZnode;//当前锁
    private CountDownLatch latch;//计数器
    private int sessionTimeout = 3000;
    private List<Exception> exception = new ArrayList<>();

    /**
     * 创建分布式锁,使用前请确认config配置的zookeeper服务可用
     *
     * @param config
     *        127.0.0.1:2181
     * @param lockName
     *        竞争资源标志,lockName中不能包含单词lock
     */
    public DistributedLock(String config, String lockName) {
        this.lockName = lockName;
        // 创建一个与服务器的连接
        try {
            this.zk = new ZooKeeper(config, this.sessionTimeout, this);
            Stat stat = this.zk.exists(this.root, false);
            if (stat == null) {
                // 创建根节点
                this.zk.create(this.root, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            this.exception.add(e);
        } catch (KeeperException e) {
            this.exception.add(e);
        } catch (InterruptedException e) {
            this.exception.add(e);
        }
    }

    /**
     * zookeeper节点的监视器
     */
    @Override
    public void process(WatchedEvent event) {
        if (this.latch != null) {
            this.latch.countDown();
        }
    }

    @Override
    public void lock() {
        if (this.exception.size() > 0) {
            throw new LockException(this.exception.get(0));
        }
        try {
            if (this.tryLock()) {
                //                System.out.println("Thread " + Thread.currentThread().getName()
                //                    + " " + this.myZnode + " get lock true");
                return;
            } else {
                this.waitForLock(this.waitNode, this.sessionTimeout);//等待锁
            }
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }
    }

    @Override
    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if (this.lockName.contains(splitStr)) {
                throw new LockException("lockName can not contains \\u000B");
            }
            //创建临时子节点
            this.myZnode = this.zk.create(
                this.root + "/" + this.lockName + splitStr, new byte[0],
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            //            System.out.println(Thread.currentThread().getName() + " : "
            //                + this.myZnode + " is created ");
            //取出所有子节点
            List<String> subNodes = this.zk.getChildren(this.root, false);
            //取出所有lockName的锁
            List<String> lockObjNodes = new ArrayList<>();
            for (String node : subNodes) {
                String _node = node.split(splitStr)[0];
                if (_node.equals(this.lockName)) {
                    lockObjNodes.add(node);
                }
            }
            Collections.sort(lockObjNodes);
            System.out.println(Thread.currentThread().getName() + " : "
                + this.myZnode + "==" + lockObjNodes.get(0));
            if (this.myZnode.equals(this.root + "/" + lockObjNodes.get(0))) {
                //如果是最小的节点,则表示取得锁
                return true;
            }
            //如果不是最小的节点，找到比自己小1的节点
            String subMyZnode = this.myZnode
                .substring(this.myZnode.lastIndexOf("/") + 1);
            this.waitNode = lockObjNodes
                .get(Collections.binarySearch(lockObjNodes, subMyZnode) - 1);
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        try {
            if (this.tryLock()) {
                return true;
            }
            return this.waitForLock(this.waitNode, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean waitForLock(String lower, long waitTime)
            throws InterruptedException, KeeperException {
        Stat stat = this.zk.exists(this.root + "/" + lower, true);
        //判断比自己小一个数的节点是否存在,如果不存在则无需等待锁,同时注册监听
        if (stat != null) {
            System.out.println("Thread " + Thread.currentThread().getName()
                + " waiting for " + this.root + "/" + lower);
            this.latch = new CountDownLatch(1);
            this.latch.await(waitTime, TimeUnit.MILLISECONDS);
            this.latch = null;
        }
        return true;
    }

    @Override
    public void unlock() {
        try {
            System.out.println(Thread.currentThread().getName() + " : "
                + "unlock " + this.myZnode);
            this.zk.delete(this.myZnode, -1);
            //            this.myZnode = null;
            //            this.zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() {
        this.lock();
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public class LockException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public LockException(String e) {
            super(e);
        }

        public LockException(Exception e) {
            super(e);
        }
    }

}