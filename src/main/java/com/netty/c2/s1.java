package com.netty.c2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.netty.c0.ByteBufferUtil.debugRead;
@Slf4j
public class s1 {
    //使用nio来理解阻塞



    @SneakyThrows
    public static void main(String[] args) throws IOException {
        // 创建服务器
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        List<SocketChannel> channels = new ArrayList<>();

        while (true){
              log.info("start........");
            //与客户端建立连接  SocketChannel 用来与客户端通信
            //accept 默认阻塞,,,等待新的连接----1111
            SocketChannel accept = serverSocketChannel.accept();
            log.info("ingo----{}",accept);
            channels.add(accept);
            accept.configureBlocking(false);//d导致read不会阻塞，没有数据read返回0
            //接受客户端的数据
            channels.stream().forEach(x->{
                try {
                    log.info("no----{}",x);
                    //  客户端没有数据，read也会阻塞，等待停止运行---222
                    x.read(buffer);
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                    log.info("after---{}",x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
