package com.matrix.test;

public class Test {
    public static void main(String[] args) {
        int i = 0;
        i = i++; // 用到了临时变量 相当于1 + 1 + 1 = 3 其中的1+1就是临时变量 假设_a是临时变量 _a = i++ -> 先赋值在运算 _a = 0 ; i++ -> i = _a = 9
        System.out.println(i);
    }

    /**
     * Test.class
     *
     *
     * public static void main(String[] args) {
     *         int i = 0;
     *         byte var10000 = i; // 相当于临时变变量
     *         int var2 = i + 1;
     *         i = var10000;
     *         System.out.println(i);
     *     }
     *
     */
}
