package net.loyintean.utils;


import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TransformUtils {

    public static void copy() {

        TestBean testBean = new TestBean();

        Optional.of(testBean).map(TestBean::getTestFiled);

        Function<TestBean, String> function = TestBean::getTestFiled;

        BiConsumer<TestBean, String> biConsumer = TestBean::setTestFiled;
    }

}

class TestBean {

    private String testFiled;

    public String getTestFiled() {
        return testFiled;
    }

    public TestBean setTestFiled(String testFiled) {
        this.testFiled = testFiled;
        return this;
    }
}