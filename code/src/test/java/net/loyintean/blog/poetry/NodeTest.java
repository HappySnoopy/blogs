/**
 * All Rights Reserved
 */
package net.loyintean.blog.poetry;

import org.junit.Before;
import org.junit.Test;

/**
 * @author linjun
 * @since 2018年2月5日
 */
public class NodeTest {
    private Node n;

    /**
     * @author linjun
     * @since 2018年2月5日
     */
    @Before
    public void setUp() {

        this.n = new Node("一麻-平",
            "啊腌扒叭巴吧芭岜疤笆粑豝嚓跨叉杈差咖瓜抓胍榻喇哈阿花哗加茄迦痂枷耞珈袈嘉佳家傢葭豭咖夸姱啦妈摩嬷趴凹咱葩杉沙莎痧鲨纱砂他她它哇洼蛙娲虾丫呀鸦哑桠查楂喳呱欻旮笳拉垃吗蚂仨裟砂趿渣揸馇挝阿八捌擦插锸耷哒嗒鎝褡发筏夹嘎刮栝鸹拉邋抹掐袷葜撒杀刹铩煞刷趿塌溻禢踏挖呷瞎鸭压押扎匝咂拶吒哳浃撒答啊茶查搽嵖猹槎楂碴苴垞蛤华哗骅铧麻嘛蟆拿扒杷爬钯耙筢琶娃霞遐瑕暇牙伢芽岈玡蚜崖涯睚衙拔茇菝跋魃察茬檫达鞑沓怛妲笪靼答跶乏伐茷垡砝阀罚嘎滑划猾夹浃郏荚铗蛱恝戛颊旯拉匣狎挟柙侠峡狭硖辖黠杂砸扎札轧闸铡喋剳");
    }

    @Test
    public void test1() {


        System.out.println(this.n.getRhyme(100));

    }

    @Test
    public void test() {

        String nodes = "啊腌扒叭巴吧芭岜疤笆粑豝嚓跨叉杈差咖瓜抓胍榻喇哈阿花哗加茄迦痂枷耞珈袈嘉佳家傢葭豭咖夸姱啦妈摩嬷趴凹咱葩杉沙莎痧鲨纱砂他她它哇洼蛙娲虾丫呀鸦哑桠查楂喳呱欻旮笳拉垃吗蚂仨裟砂趿渣揸馇挝阿八捌擦插锸耷哒嗒鎝褡发筏夹嘎刮栝鸹拉邋抹掐袷葜撒杀刹铩煞刷趿塌溻禢踏挖呷瞎鸭压押扎匝咂拶吒哳浃撒答啊茶查搽嵖猹槎楂碴苴垞蛤华哗骅铧麻嘛蟆拿扒杷爬钯耙筢琶娃霞遐瑕暇牙伢芽岈玡蚜崖涯睚衙拔茇菝跋魃察茬檫达鞑沓怛妲笪靼答跶乏伐茷垡砝阀罚嘎滑划猾夹浃郏荚铗蛱恝戛颊旯拉匣狎挟柙侠峡狭硖辖黠杂砸扎札轧闸铡喋剳";

        System.out.println(nodes);

        char[] chars = nodes.toCharArray();

        System.out.println(chars.length);

        for (char c : chars) {
            System.out.println(c);
        }
    }

}
