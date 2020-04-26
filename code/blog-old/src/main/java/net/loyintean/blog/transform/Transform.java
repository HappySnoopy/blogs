package net.loyintean.blog.transform;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Transform {

    /**
     * 预定义一个转换器。其中的cube可以考虑放出来复用。
     *
     * 类名、方法名可以考虑缩短。
     */
    private static final Transformer<FromVo, ToVo> transformer = Transformer.build(FromVo.class, ToVo.class)
            .transform(FromVo::getName, ToVo::setUserName).transform(FromVo::getAge, ToVo::setCurrentAge);

    public static void main(String[] args) {
        FromVo from = new FromVo();
        from.setAge(10);
        from.setName(RandomStringUtils.randomAlphanumeric(16));

        ToVo to = new ToVo();
        to.setCurrentAge(from.getAge());
        to.setUserName(from.getName());


        transformer.transform(from, to);
        System.out.println(to.getCurrentAge() == from.getAge());
        System.out.println(to.getUserName() == from.getName());

    }

    private static class Transformer<F, T> {

        private List<Cube> cubeList = new ArrayList<>();

        private Transformer() {
        }

        public static <F, T> Transformer<F, T> build(Class<F> clazF, Class<T> clazT) {
            return new Transformer<>();
        }

        public void transform(F from, T to) {
            cubeList.forEach(cube -> cube.setter.accept(to, cube.getter.apply(from)));
        }

        public <V> Transformer<F, T> transform(Function<F, V> getter, BiConsumer<T, V> setter) {
            cubeList.add(new Cube(getter, setter));
            return this;
        }

        private class Cube<F, V, T> {
            Function<F, V> getter;
            BiConsumer<T, V> setter;

            public Cube(Function<F, V> getter, BiConsumer<T, V> setter) {
                this.getter = getter;
                this.setter = setter;
            }
        }
    }
}
