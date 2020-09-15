package com.matrix.test;

public class Test4 {
    public static void main(String[] args) throws Exception{
        // 字节码乱码问题 乱码的思路
        String s = "???";
        byte[] bs = s.getBytes("ISO-8859-1"); // 恢复成原始的字符码序列
        String res = new String(bs, "UTF-8");
        System.out.println(res);

    }
}
