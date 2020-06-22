package jdk8;

public class MySubClass /* extends MyBaseClass */ implements MyInterface1, MyInterface2 {

    @Override
    public String getName() {
        return MyInterface1.super.getName();
    }
}
