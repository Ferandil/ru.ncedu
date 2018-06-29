package ru.ncedu.kodratenkov.workwithrefl;

import java.io.Serializable;
import java.util.List;

class Test implements Serializable, Cloneable {
    private int field;
    protected String[] strings;
    private static final Long serialNumber = 500L;
    public List array;

    public Test() {

    }

    public Test(Integer field) {
        this.field = field;
    }

    @Deprecated
    protected static void method(String[] params) { }


    public void foo() {
        System.out.println("FOO");
    }

    @Override
    public String toString() {
        return "Test{" +
                "field=" + field +
                '}';
    }
}
