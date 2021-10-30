package com.netty.c1;

import java.nio.ByteBuffer;

import static com.netty.c0.ByteBufferUtil.debugAll;

public class a3 {
    public static void main(String[] args) {
        //分配空间 方法
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put(new byte[]{'a','b','c','d'});
        buffer.rewind();//从头看开始读
        debugAll(buffer);
        System.out.println((char) buffer.get());
        buffer.mark();//做标记，从这开始记
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.reset();// 从标记处开始读
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get(2));
    }
}
