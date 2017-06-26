/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.advance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.loyintean.blog.rest.client.RestClientFactory;

/**
 * @author linjun
 * @since 2017年5月22日
 */
public class Apply {

    private static final RestClientFactory factory = new RestClientFactory();
    static {
        Apply.factory.setUserName("10020865");
        Apply.factory.setPassWord("thread1224!");
    }


    private static final List<String> URL = Arrays.asList(
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/317969?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/615986?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/657897?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/657902?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/703283?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/813086?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/813098?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/816452?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/825862?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/832723?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/844550?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/844582?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/844583?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/853256?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/855308?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/855564?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/859442?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/860444?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/865325?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/865828?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/868970?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/872747?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/874132?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/880037?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/880099?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/884292?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/884429?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/889575?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/890165?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/890667?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/893353?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/905166?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/906471?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/906730?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/909599?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/914846?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/915006?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/920381?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/921370?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/921496?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/921954?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/923164?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/923527?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/925719?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/926067?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/927209?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/927966?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/928321?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/928431?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/930652?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/933423?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/933563?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/936429?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/939392?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/945882?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/950079?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/953018?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/953479?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/962184?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/963480?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/964070?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/964814?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/965504?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/972968?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/974478?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/976118?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/976399?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/979679?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/979738?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/980014?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/980599?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/981887?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/982872?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/982939?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/983920?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/984673?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/984863?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/985056?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/986268?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/986346?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/986528?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/986601?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/986637?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/986782?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/986784?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/987312?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/987417?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/987891?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/987970?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/988207?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/988245?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/988892?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/989705?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/990794?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/991529?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/991579?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/991842?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/991867?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/992177?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/992466?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/992497?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/992749?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/992951?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/994112?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/994989?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/995863?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/996545?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/996947?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/997984?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/998320?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/998605?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/998795?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/999488?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/999836?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/999963?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/999975?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1000371?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1001991?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1002346?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1002435?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1002490?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1003677?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1005914?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1014051?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1021348?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1024455?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1024583?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1024847?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1026714?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1028703?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1029530?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1030594?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1030841?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1032496?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1033819?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1036712?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1036726?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1036762?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1040942?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1043309?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1047044?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1052722?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1057545?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1057742?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1059495?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1062424?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1063089?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1065775?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1088721?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1089712?type=NOVALIDATE",
        "https://thread.ucredit.com/thread_portfolio/rest/bizAccountPlan/inAdvanceApply/1103387?type=NOVALIDATE");

    private static final List<String> BODY = Arrays
        .asList("{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-10\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-04-24\"}", "{\"repayDay\":\"2017-04-24\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-04-26\"}",
            "{\"repayDay\":\"2017-04-28\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-16\"}", "{\"repayDay\":\"2017-04-19\"}",
            "{\"repayDay\":\"2017-05-17\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-06\"}", "{\"repayDay\":\"2017-05-08\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-08\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-16\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-10\"}",
            "{\"repayDay\":\"2017-05-18\"}", "{\"repayDay\":\"2017-05-18\"}",
            "{\"repayDay\":\"2017-04-22\"}", "{\"repayDay\":\"2017-04-28\"}",
            "{\"repayDay\":\"2017-04-29\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-30\"}", "{\"repayDay\":\"2017-04-30\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-04-29\"}",
            "{\"repayDay\":\"2017-05-11\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-05-11\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-12\"}", "{\"repayDay\":\"2017-05-12\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-06\"}",
            "{\"repayDay\":\"2017-05-07\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-12\"}",
            "{\"repayDay\":\"2017-04-22\"}", "{\"repayDay\":\"2017-04-22\"}",
            "{\"repayDay\":\"2017-04-23\"}", "{\"repayDay\":\"2017-04-29\"}",
            "{\"repayDay\":\"2017-05-21\"}", "{\"repayDay\":\"2017-04-27\"}",
            "{\"repayDay\":\"2017-04-28\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-04-22\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-04\"}", "{\"repayDay\":\"2017-05-19\"}",
            "{\"repayDay\":\"2017-05-19\"}", "{\"repayDay\":\"2017-05-18\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-23\"}", "{\"repayDay\":\"2017-04-23\"}",
            "{\"repayDay\":\"2017-04-23\"}", "{\"repayDay\":\"2017-04-26\"}",
            "{\"repayDay\":\"2017-04-23\"}", "{\"repayDay\":\"2017-05-03\"}",
            "{\"repayDay\":\"2017-04-24\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-26\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-03\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-05-02\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-04-23\"}",
            "{\"repayDay\":\"2017-05-01\"}", "{\"repayDay\":\"2017-05-07\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-02\"}",
            "{\"repayDay\":\"2017-05-02\"}", "{\"repayDay\":\"2017-05-02\"}",
            "{\"repayDay\":\"2017-05-15\"}", "{\"repayDay\":\"2017-05-02\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-07\"}",
            "{\"repayDay\":\"2017-05-02\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-05\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-05-10\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-21\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-09\"}",
            "{\"repayDay\":\"2017-05-09\"}", "{\"repayDay\":\"2017-05-16\"}",
            "{\"repayDay\":\"2017-05-15\"}", "{\"repayDay\":\"2017-05-11\"}",
            "{\"repayDay\":\"2017-04-24\"}", "{\"repayDay\":\"2017-04-24\"}",
            "{\"repayDay\":\"2017-04-24\"}", "{\"repayDay\":\"2017-04-25\"}",
            "{\"repayDay\":\"2017-04-25\"}", "{\"repayDay\":\"2017-04-30\"}",
            "{\"repayDay\":\"2017-04-28\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-05-06\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-04-30\"}", "{\"repayDay\":\"2017-05-01\"}",
            "{\"repayDay\":\"2017-05-15\"}", "{\"repayDay\":\"2017-05-15\"}",
            "{\"repayDay\":\"2017-05-01\"}", "{\"repayDay\":\"2017-05-08\"}",
            "{\"repayDay\":\"2017-05-08\"}", "{\"repayDay\":\"2017-04-26\"}",
            "{\"repayDay\":\"2017-05-20\"}", "{\"repayDay\":\"2017-05-12\"}",
            "{\"repayDay\":\"2017-05-20\"}", "{\"repayDay\":\"2017-05-04\"}",
            "{\"repayDay\":\"2017-04-27\"}", "{\"repayDay\":\"2017-05-03\"}",
            "{\"repayDay\":\"2017-05-03\"}", "{\"repayDay\":\"2017-05-18\"}",
            "{\"repayDay\":\"2017-05-12\"}", "{\"repayDay\":\"2017-04-30\"}");

    private static final String REPAY_URL = "https://thread.ucredit.com/thread_portfolio/rest/bizAccountEvent/REPAY";

    /*
     * "{\"lrId\":816452,\"bookDate\":\"2017-05-19\"}",
     * "{\"lrId\":855308,\"bookDate\":\"2017-04-19\"}",
     * "{\"lrId\":1024455,\"bookDate\":\"2017-04-24\"}"
     */
    private static final List<String> REPAY_BODY = Arrays
        .asList("{\"lrId\":816452,\"bookDate\":\"2017-05-19\"}",
            "{\"lrId\":855308,\"bookDate\":\"2017-04-19\"}");
    /**
     * @author linjun
     * @since 2017年5月22日
     * @param args
     */
    public static void main(String[] args) {

        for (int index = 0; index < Apply.REPAY_BODY.size(); index++) {

            try {

                Map<String, Object> result;

                //            result = Apply.factory.newClient()
                //                .setUrl(Apply.URL.get(index)).responseAs(Map.class)
                //                .setBody(Apply.BODY.get(index))
                //                .addHeader("content-type", "application/json").post();
                //            System.out.println(result);

                result = Apply.factory.newClient().setUrl(Apply.REPAY_URL)
                    .responseAs(Map.class).setBody(Apply.REPAY_BODY.get(index))
                    .addHeader("content-type", "text/html").post();
                System.out.println(result);
            System.out.println(Apply.REPAY_BODY.get(index));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
