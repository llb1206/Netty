package com.netty.c1;

import java.nio.ByteBuffer;

public class Allocate1 {
    public static void main(String[] args) {
        //分配空间
        System.out.println(ByteBuffer.allocate(10).getClass());
        System.out.println(ByteBuffer.allocateDirect(10).getClass());
        /**
         *
         * class java.nio.HeapByteBuffer  -读写效率较低  受到垃圾回收影响
         * class java.nio.DirectByteBuffer  - 直接内存效率高 少一次拷贝，不会受到垃圾回收影响/分配内存比较慢
         */
    }
}
