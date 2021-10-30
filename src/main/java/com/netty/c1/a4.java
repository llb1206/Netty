package com.netty.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.netty.c0.ByteBufferUtil.debugAll;

public class a4 {
    public static void main(String[] args) {
        //1，将字符串转为bytebuffer  --- 三种方法
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.put("hello".getBytes());
        debugAll(allocate);


        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");

        debugAll(hello);

        ByteBuffer wrap = ByteBuffer.wrap("hello".getBytes());
        debugAll(wrap);
        // 转换回来
        String s = StandardCharsets.UTF_8.decode(wrap).toString();
        System.out.println(s);
    }
}
