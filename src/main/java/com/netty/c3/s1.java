package com.netty.c3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.netty.c0.ByteBufferUtil.debugRead;

@Slf4j
public class s1 {
    /**
     * selector 管理多个channel，检测channel事件发生，交给线程执行
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);
        //将来事件发生时，可以知道哪个channel发生事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        log.info("rest",sscKey.hashCode());
        /**
         * 事件的四种
         * accept  会有连接请求
         * connect 客户端建立连接触发
         * read
         * write
         */
        //interest 感兴趣
        sscKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true){
            //没有事件  阻塞，有事件继续
            selector.select();

            //处理事件   包含了所有发生的事件，会往这个集合里面放，用过需要移除
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            //触发事件后，应该主动移除key，要不会nulle
            Iterator<SelectionKey> keyIterator = selectionKeySet.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                //accept()如果没有处理，他不会阻塞,事件发生后，要么处理，要么取消cancel，不能置之不理
                //触发事件后，应该主动移除key，要不会nulle
                keyIterator.remove();


                 //判断事件类型
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    SelectionKey sckey = accept.register(selector, 0, null);
                    //关注读事件
                    sckey.interestOps(SelectionKey.OP_READ);
                    System.out.println(accept);

                }else if(selectionKey.isReadable()){
                    //selectionKey.cancel();
                    try {
                        SocketChannel channel = (SocketChannel)selectionKey.channel();
                        ByteBuffer allocate = ByteBuffer.allocate(16);
                        int read = channel.read(allocate);//如果是正常方法，read返回值是-1
                        if(read==-1){
                            selectionKey.cancel();
                        }else {allocate.flip();
                            debugRead(allocate);}
                    } catch (IOException e) {
                        e.printStackTrace();
                        selectionKey.cancel();
                    }
                }
            }
        }
    }
}
