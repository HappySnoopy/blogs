/**
 * All Rights Reserved
 */
package net.loyintean.blog.poetry;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 韵脚选择器
 *
 * @author Snoopy
 * @since 2018年2月5日
 */
public class RhymeChooser {

    private List<Rhyme> rhymes;

    private RhymeChooser() {

        this.rhymes = new ArrayList<>();

        // 添加中华新韵
        this.rhymes.add(new ChineseNewRhyme());
        // 添加平水韵
        this.rhymes.add(new FlatWaterRhyme());
        // 添加词林正韵
        this.rhymes.add(new WordForestFormalRhyme());

    }

    public Rhyme choose(int i) {
        return this.rhymes.get(i % this.rhymes.size());

    }

    /**
     * @param args
     * @author Snoopy
     * @since 2018年2月5日
     */
    public static void main(String[] args) {
        RhymeChooser chooser = new RhymeChooser();

        //掷骰子
        int dice;

        // 选择韵部
        dice = RandomUtils.nextInt(0, 100);

        Rhyme rhyme = chooser.choose(dice);

        System.out.println(rhyme.getRhymeName());

        // 选择韵脚分类
        dice = RandomUtils.nextInt(0, 100);
        Node node = rhyme.getNode(dice);

        System.out.println(node.getType());

        // 选择五个韵脚
        Stream.iterate(dice, d -> RandomUtils.nextInt(0, 1000)).limit(5)
            .map(index -> node.getRhyme(index)).forEach(System.out::println);

    }
}
