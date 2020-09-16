package com.matrix.test;

import java.lang.reflect.Field;

public class Test5 {
    public static void main(String[] args) throws Exception{

//        String s = " a b ";
////        s.trim(); // 只能去掉半角不能去掉全角
//        s = s.trim();
//        System.out.println(s);


        // 反射是可以改变字符串的内容的

        String s = "abc";
        Class clazz = s.getClass();
        Field f = clazz.getDeclaredField("value");
        f.setAccessible(true); // 绕过访问机制
        char[] ch = (char[])f.get(s);
        ch[1] = '!';

        System.out.println(s);

    }
}
