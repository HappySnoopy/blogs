/**
 * All Rights Reserved
 */
package net.loyintean.blog.cicadas;

import java.util.Arrays;

/**
 * 森林里有知了，有鸟。
 *
 * @author linjun
 * @since 2017年8月28日
 */
public class Forest {
    /**
     * 繁殖周期上限。
     *
     * @author linjun
     * @since 2017年8月28日
     */
    private static final int PERIOD_LIMIT = 20;

    /**
     * 知了种群。
     * <p>
     * 下标是种群繁殖周期；内容是中群数量<br>
     * 知了的生长规则：<br>
     * 1. 如果当季是自己的繁殖季(繁殖周期%当前年数==0)，那么知了数量翻倍 <br>
     * 2. 如果当季是鸟类的繁殖季，那么知了期末数量=翻倍后数量-鸟类期初数量*2<br>
     *
     * @author linjun
     * @since 2017年8月28日
     */
    private static final long[] cicadases = new long[Forest.PERIOD_LIMIT + 1];

    /**
     * 鸟类种群
     * <p>
     * 下标是种群繁殖周期；内容是中群数量<br>
     * 鸟类的生长规则：<br>
     * 1. 如果当季是知了的繁殖季(繁殖周期%当前年数==0)，那么：<br>
     * 1.1 如果还有知了，那么鸟类期末数量=鸟类期初数量+知了数量
     * 1.2 如果没有知了，那么鸟类期末数量=鸟类期初数量/2
     *
     * @author linjun
     * @since 2017年8月28日
     */
    private static final long[] birds = new long[Forest.PERIOD_LIMIT + 1];

    /**
     * @author linjun
     * @since 2017年8月28日
     * @param args
     */
    public static void main(String[] args) {

        Forest.init();
        long[] c = Arrays.copyOf(Forest.cicadases, Forest.cicadases.length);
        long[] b = Arrays.copyOf(Forest.birds, Forest.birds.length);

        // 从第一年开始，运行若干年
        for (int year = 1; year < 100; year++) {

            // 鸟类能吃到的知了
            long cicadasSum = 0;

            // 知了繁殖
            for (int i = 1; i <= Forest.PERIOD_LIMIT; i++) {
                if (year % i == 0) {
                    // 知了先繁殖：
                    // 知了繁殖：如果是知了的繁殖季，那么知了数量翻n倍
                    Forest.cicadases[i] = Forest.cicadases[i] * 2;

                    // 累加上知了数量
                    cicadasSum += Forest.cicadases[i];
                }
            }

            // 鸟吃知了，知了减少
            for (int i = 1; i <= Forest.PERIOD_LIMIT; i++) {
                if (year % i == 0) {
                    // 按每只鸟吃n只知了来计算
                    Forest.cicadases[i] -= Forest.birds[i] * 5;
                    // 归零矫正
                    Forest.cicadases[i] = Long.max(Forest.cicadases[i], 0);

                    // 累加上知了数量
                    cicadasSum += Forest.cicadases[i];
                }
            }

            // 当年鸟总数
            long birdSum = 0;
            // 鸟繁殖
            for (int i = 1; i <= Forest.PERIOD_LIMIT; i++) {
                if (year % i == 0) {
                    // 如果知了够吃（cicadasSum没吃光），则鸟类正增长：数量*n
                    if (cicadasSum > 0) {
                        Forest.birds[i] *= 1.5;
                    } else {
                        // 如果不够吃，鸟类被饿死，数量除以2
                        Forest.birds[i] *= 0.1;
                        // 归零矫正
                        Forest.birds[i] = Long.max(Forest.birds[i], 0);
                    }

                    birdSum += Forest.birds[i];
                }

            }

            System.out.println(
                "第 " + year + " 年，蝉有：" + Arrays.toString(Forest.cicadases)
                    + " 只；鸟有：" + Arrays.toString(Forest.cicadases) + " 只");

        }

        for (int i = 1; i <= Forest.PERIOD_LIMIT; i++) {

            System.out
                .print(i + " 季蝉数量从：" + c[i] + " 变更为： " + Forest.cicadases[i]
                    + "，倍数：" + Forest.cicadases[i] / c[i] + "; ");
            System.out.println(i + " 季鸟数量从：" + b[i] + " 变更为： " + Forest.birds[i]
                + "，倍数：" + Forest.birds[i] / b[i]);

        }

    }

    /**
     * @author linjun
     * @since 2017年8月28日
     */
    private static void init() {

        for (int i = 1; i <= Forest.PERIOD_LIMIT; i++) {
            Forest.cicadases[i] = 500;
            Forest.birds[i] = 50;
        }

    }
}
