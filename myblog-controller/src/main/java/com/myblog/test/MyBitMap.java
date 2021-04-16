package com.myblog.test;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2021/4/16
 */
public class MyBitMap {

    private byte[] bytes;

    private int initSize;

    public MyBitMap(int size) {
        if (size <= 0) {
            return;
        }
        initSize = size / (8) + 1;
        bytes = new byte[initSize];
    }

    public void set(int number) {
        // 对一个数字进行右移动3位，相当于除以8
        int index = number >> 3;
        // 相当于number % 8，获取到byte[index]的位置
        int position = number & 0x07;
        // 进行|或运算，参加运算的两个对象只要有一个为1，则其值为1
        bytes[index] |= 1 << position;
    }

    public boolean contain(int number) {
        int index = number >> 3;
        int position = number & 0x07;
        return (bytes[index] & (1 << position)) != 0;
    }

    public static void main(String[] args) {
        MyBitMap bitMap = new MyBitMap(1000000);
        for (int i = 0; i < 1000000; i++) {
            bitMap.set(i);
        }
        System.out.println(bitMap.contain(1000000));
    }

}
