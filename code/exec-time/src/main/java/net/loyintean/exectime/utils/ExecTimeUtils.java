package net.loyintean.exectime.utils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * 一个用来测执行时间的简单工具
 *
 * @author Snoopy
 * @date 2020年11月24日
 */
public class ExecTimeUtils {

    /**
     * 测一次执行的时间
     *
     * @param <T>      实际返回的数据类型
     * @param supplier 实际操作
     * @return 带了执行时间和实际结果的结果
     */
    public static <T> ExecTimeResult<T> execTime(Supplier<T> supplier) {
        ExecTimeResult<T> execTimeResult = new ExecTimeResult<>();
        long start = System.currentTimeMillis();
        execTimeResult.setResult(supplier.get());
        long end = System.currentTimeMillis();
        execTimeResult.setTime(end - start);
        return execTimeResult;
    }

    /**
     * 测循环执行N次的时间
     *
     * @param <T>       实际返回的数据类型
     * @param supplier  实际操作
     * @param loopTimes 循环次数
     * @return 带了执行时间和实际结果的结果
     */
    public static <T> ExecTimeResult<List<T>> execTime(Supplier<T> supplier, long loopTimes) {
        ExecTimeResult<List<T>> execTimeResult = new ExecTimeResult<>();
        long start = System.currentTimeMillis();

        execTimeResult.setResult(LongStream.range(0, loopTimes)
                .mapToObj(l -> supplier.get())
                .collect(Collectors.toList()));

        long end = System.currentTimeMillis();
        execTimeResult.setTime(end - start);
        return execTimeResult;
    }

    /**
     * 执行时间计时的结果
     *
     * @param <T> the type parameter
     * @author Snoopy
     * @date 2020年11月24日
     */
    public static class ExecTimeResult<T> {
        private long time;
        private T result;

        /**
         * Gets time.
         *
         * @return the time
         */
        public long getTime() {
            return time;
        }

        /**
         * Sets time.
         *
         * @param time the time
         */
        public void setTime(long time) {
            this.time = time;
        }

        /**
         * Gets result.
         *
         * @return the result
         */
        public T getResult() {
            return result;
        }

        /**
         * Sets result.
         *
         * @param result the result
         */
        public void setResult(T result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "ExecTimeResult{" +
                    "time=" + time +
                    ", result=" + result +
                    '}';
        }
    }
}
