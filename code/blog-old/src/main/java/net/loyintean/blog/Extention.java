package net.loyintean.blog;

import java.util.ArrayList;
import java.util.List;

public class Extention {
    public static void main(String[] args) {

        List<? super Inner> list = new ArrayList<>();
        list.add(new Son());
        list.add(new Inner());

        List<? extends Inner> list1 = new ArrayList<>();
        Father f1 = list1.get(0);
        Inner i1 = list1.get(2);

    }
}

class Father {
}

class Inner extends Father {
}

class Son extends Inner {
}


class Service0<I extends Inner, O extends Inner> {
    public O doSth(I i) {
        return null;
    }

}

class Service1 extends Service0<Son, Son> {
    public Son doSth(Father f) {
        return new Son();
    }
}

