package com.matrix.test;

/**
 * 多态 一个对象的多种形态 一个对象在不同的场合下表现出的不同的状态
 */
public class Test1 {

    public static void main(String[] args) {
        AA aa = new BB(); // 用BB代替AA对象
        System.out.println(aa.getResult()); // 调用的是AA的方法 实际上走的BB的方法
        /**
         * 动态绑定机制
         * JVM 在执行对象的成员方法时 会将这个方法和对象的实际内存进行绑定 然后调用
         * 当前 new BB() 实际内存是BB 他会去BB里面看看有没有方法
         * 当把BB的方法注掉的时候  因为动态绑定只和 方法有关系 所以他只能去调用AA 的方法和变量了
         */
    }
}

class AA {
    public int i = 10;
//    public int getResult() {
//        return i + 10;
//    }
public int getResult() {
    return getI() + 10;
}
    public int getI() {
        return i;
    }
}

class BB extends AA{
    public int i = 20;
    public int getResult() {
        return i + 20;
    }
    public int getI() {
        return this.i; // 默认是this 不是super
    }
}
