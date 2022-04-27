package com.example.demo;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;


@SpringBootTest
public class SocketTest {

//    public static void main(String[] args) throws Exception { // 创 建 ServerSocketChannel， 监 听 8080 端 口
//        ServerSocketChannel ssc = ServerSocketChannel.open();
//        ssc.socket().bind(new InetSocketAddress(8080)); //设置为非阻塞模式 ssc.configureBlocking(false); //为ssc注册选择器
//        Selector selector = Selector.open();
//        ssc.register(selector, SelectionKey.OP_ACCEPT); //创建处理器
//        Handler handler = new Handler(1024);
//        while (true) { // 等待请求，每次等待阻塞3s，超过3s后线程继 续 向 下 运 行，如 果 传 入 0 或 者 不 传 参 数 将 一 // 直 阻 塞 if(selector.select(3000)==0) {
//            System.out.println("等待请求超时……");
//            continue;
//        }
//        System.out.println("处理请求……"); // 获取待处理的SelectionKey
//        Iterator keyIter = selector.selectedKeys().iterator();
//        while (keyIter.hasNext()) {
//            SelectionKey key = (SelectionKey) keyIter.next();
//            try { // 接 收 到 连 接 请 求 时 if(key.isAcceptable()) {
//                handler.handleAccept(key);
//            } // 读 数 据 if(key.isReadable()) { handler.handleRead(key); } }
//            catch (IOException ex) {
//                keyIter.remove();
//                continue;
//            } // 处理完后，从待处理的SelectionKey迭代器中移除当前所使用的key
//            keyIter.remove();
//        }
//    }
//
//    private static class Handler {
//        private int bufferSize = 1024;
//        private String localCharset = "UTF-8";
//
//        { // 将buffer转换为读状态 buffer.flip(); // 将buffer中接收到的值按localCharset格式 编 码 后 保 存 到 receivedString
//            String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
//            System.out.println("received from client: " + receivedString); // 返 回 数 据 给 客 户 端 String
//            sendString = "received data: " + receivedString;
//            buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
//            sc.write(buffer); // 关闭Socket sc.close(); } } }
//        }
//
//        public Handler() {
//        }
//
//        public Handler(int bufferSize) {
//            this(bufferSize, null);
//        }
//
//        public Handler(String LocalCharset) {
//            this(-1, LocalCharset);
//        }
//
//        public Handler(int bufferSize, String localCharset) {
//            if (bufferSize > 0) this.bufferSize = bufferSize;
//            if (localCharset != null) this.localCharset = localCharset;
//        }
//
//        public void handleAccept(SelectionKey key) throws IOException {
//            SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
//            sc.configureBlocking(false);
//            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
//        }
//
//        public void handleRead(SelectionKey key) throws IOException { // 获取channel
//            SocketChannel sc = (SocketChannel) key.channel(); // 获 取 buffer 并 重 置 ByteBuffer
//            buffer = (ByteBuffer) key.attachment();
//            buffer.clear(); // 没有读到内容则关闭 if(sc.read(buffer)==-1){
//            sc.close();
//        }
//
//    }
}
