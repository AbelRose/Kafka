package com.matrix.test;

/**
 * 多态 一个对象的多种形态 一个对象在不同的场合下表现出的不同的状态
 */
public class Test2 {

    public static void main(String[] args) {

//1        AAA aaa = new BBB(); // 只和AAA有关系 只看前面的
//1       test(aaa);

//2        byte b = 10; // 只和前面有关 和 10 int 没有关系
//2        test(b);

    }

//1    public static void test(AAA aaa) {
//1        System.out.println("aaa");
//1    }
//1    public static void test(BBB bbb) {
//1        System.out.println("bbb");
//1    }

//2    public static void test(byte b) { // 如果此时把这个注掉了 会走 short 16位  byte 是 8位 会升级 向16位走最快
//2       System.out.println("b");
//2    }
//2    public static void test(int i) {
//2        System.out.println("i");
//2    }
//2    public static void test(char c) { // 取值范围是0-65535 没有负数 不能代替byte和short 所以不走char 只能走int 32位的
//2        System.out.println("c");
//2    }
//2    public static void test(short s) {
//2        System.out.println("s");
//2    }


}

class AAA {

}

class BBB extends AAA{

}
