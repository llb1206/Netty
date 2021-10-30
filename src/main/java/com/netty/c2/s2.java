package com.netty.c2;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class s2 {


    @SneakyThrows
    public static void main(String[] args) throws IOException {

        SocketChannel open = SocketChannel.open();
        open.connect(new InetSocketAddress("localhost",8080));
        System.out.println("waiting..........");
    }
}
