package net.loyintean.blog.ip138;

import net.loyintean.blog.rest.client.RestClientFactory;

import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 多线程版本
 * <p>
 * 一个线程池用于从ip138获取数据。
 * <p>
 * 一个线程池用于解析数据。
 * <p>
 * 一个线程池用于写文件。
 *
 * @author 林俊
 */
public class Spider4Ip138Concurrency {

    private static final int processorCount = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor readerThreadPool = new ThreadPoolExecutor(processorCount,
            processorCount * 3, 30, TimeUnit.MINUTES, new LinkedBlockingDeque(), new NamedThreadFactory("爬虫线程"));
    private static final ThreadPoolExecutor parserThreadPool = new ThreadPoolExecutor(1, processorCount, 30,
            TimeUnit.MINUTES, new LinkedBlockingDeque(), new NamedThreadFactory("解析线程"));
    private static final ThreadPoolExecutor writerThreadPool = new ThreadPoolExecutor(processorCount,
            processorCount * 2, 30, TimeUnit.MINUTES, new LinkedBlockingDeque(), new NamedThreadFactory("写入线程"));


    private static FileWriter writer;
    private static CountDownLatch countDown;


    public static void main(String[] args) {
        long mobile = 13400000000L;
        int count = 100;
        try {
            // 初始化文件
            writer = new FileWriter(new File("C:\\Users\\LAP02F2170925\\Desktop\\ip138.txt"));
            countDown = new CountDownLatch(count);

            // 启动爬虫线程
            for (int i = 0; i < count; i++) {
                readerThreadPool.submit(new Ip138Reader(mobile + i));
            }

            // 全部写完之后，关闭writer
            countDown.await();
            System.out.println("全部" + count + "个任务都已完成");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 负责爬取ip138的网页数据
     * <p>
     * 这个可以做对象池
     *
     * @author 林俊
     */
    static class Ip138Reader implements Runnable {
        private static final String URL_IP_138 = "http://www.ip138.com:8080/search.asp";
        private static final RestClientFactory Factory = new RestClientFactory();

        private long mobileNo;

        public Ip138Reader(long mobileNo) {
            this.mobileNo = mobileNo;
        }

        @Override
        public void run() {
            String result = Factory.newClient().setUrl(URL_IP_138).addRequestParam("action", "mobile")
                    .addRequestParam("mobile", Long.toString(mobileNo)).responseAs(String.class).addHeader("Accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                    .addHeader("Connection", "keep-alive").addHeader("Host", "www.ip138.com")
                    .addHeader("Upgrade-Insecure-Requests", "1").addHeader("User-Agent",
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
                    .addHeader("Content-Type", "text/html; charset=gb2312").get();

            // 默认是ISO-8859-1，页面指定为GB-2312的，需要转一次编码
            try {
                result = new String(result.getBytes(StandardCharsets.ISO_8859_1), "GB2312");
                System.out.println(Thread.currentThread().getName() + " 完成爬取任务：" + mobileNo);
                // 提交解析任务
                parserThreadPool.submit(new Ip138Parser(new HashMap.SimpleEntry(mobileNo, result)));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    static class Ip138Parser implements Runnable {

        private DocumentParser parser;
        private Spider4Ip138.Ip138Callback callback;

        private AbstractMap.SimpleEntry<Long, String> htmlData;

        public Ip138Parser(AbstractMap.SimpleEntry<Long, String> simpleEntry) {
            try {
                parser = new DocumentParser(DTD.getDTD(""));
            } catch (IOException e) {
                e.printStackTrace();
            }
            callback = new Spider4Ip138.Ip138Callback();
            this.htmlData = simpleEntry;
        }

        @Override
        public void run() {
            Spider4Ip138.Ip138Data4Mobile mobileData = new Spider4Ip138.Ip138Data4Mobile();
            mobileData.setMobile(htmlData.getKey());

            callback.setMobile(mobileData);

            try (BufferedReader reader = new BufferedReader(new CharArrayReader(htmlData.getValue().toCharArray()))) {
                parser.parse(reader, callback, true);
                System.out.println(Thread.currentThread().getName() + " 完成解析任务：" + mobileData);
                writerThreadPool.submit(new Ip138Writer(mobileData));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Ip138Writer implements Runnable {

        private Spider4Ip138.Ip138Data4Mobile mobileData;

        public Ip138Writer(Spider4Ip138.Ip138Data4Mobile mobileData) {
            this.mobileData = mobileData;
        }

        @Override
        public void run() {
            StringBuilder fileContext = new StringBuilder(512);
            fileContext.append(mobileData.getMobile()).append("\t").append(mobileData.getAddress()).append("\t")
                    .append(mobileData.type).append("\t").append(mobileData.areaCode).append("\t")
                    .append(mobileData.postCode).append(System.lineSeparator());
            try {
                writer.append(fileContext);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " 完成写入任务：" + fileContext);
                countDown.countDown();
            }
        }
    }

    static class NamedThreadFactory implements ThreadFactory {
        private final AtomicLong threadCounter = new AtomicLong(0);
        private final String name;

        public NamedThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, name + threadCounter.getAndDecrement());
        }
    }
}
