package com.netty.c1;

import java.nio.ByteBuffer;

import static com.netty.c0.ByteBufferUtil.debugAll;

public class TestByteBufferWrite {
    public static void main(String[] args) {
        //分配空间 方法
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        debugAll(buffer);
        buffer.put(new byte[]{0x61,0x62,0x63});
        debugAll(buffer);
        System.out.println(buffer.get());
        buffer.flip();
        System.out.println(buffer.get());
        buffer.compact();
        debugAll(buffer);
        buffer.put(new byte[]{0x65,0x66,0x67});
        debugAll(buffer);
    }
}
