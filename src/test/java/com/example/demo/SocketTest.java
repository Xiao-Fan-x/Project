package com.example.demo;


import com.hankcs.hanlp.HanLP;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
/**
 * 相似度匹配算法
 * （据说）由俄国人Vladimir Levenshtein在1965年发明
 * 原理：返回将第一个字符串转换(删除、插入、替换)成第二个字符串的编辑次数。
 * 次数越少，意味着字符串相似度越高
 */

class Test {

    public static void main(String[] args) {

        String Str_1="城市范围内将多个校园网互联构成城域网。多个城域网又通过路由器与光纤接入作为国家 级或区域主干网的广域网";
        String Str_2="城市范围有校园网";
        //length
        int Length1=Str_1.length();
        int Length2=Str_2.length();

        int Distance=0;
        if (Length1==0) {
            Distance=Length2;
        }
        if(Length2==0)
        {
            Distance=Length1;
        }
        if(Length1!=0&&Length2!=0){
            int[][] Distance_Matrix=new int[Length1+1][Length2+1];
            //编号
            int Bianhao=0;
            for (int i = 0; i <= Length1; i++) {
                Distance_Matrix[i][0]=Bianhao;
                Bianhao++;
            }
            Bianhao=0;
            for (int i = 0; i <=Length2; i++) {
                Distance_Matrix[0][i]=Bianhao;
                Bianhao++;
            }


            char[] Str_1_CharArray=Str_1.toCharArray();
            char[] Str_2_CharArray=Str_2.toCharArray();


            for (int i = 1; i <= Length1; i++) {
                for(int j=1;j<=Length2;j++){
                    if(Str_1_CharArray[i-1]==Str_2_CharArray[j-1]){
                        Distance=0;
                    }
                    else{
                        Distance=1;
                    }

                    int Temp1=Distance_Matrix[i-1][j]+1;
                    int Temp2=Distance_Matrix[i][j-1]+1;
                    int Temp3=Distance_Matrix[i-1][j-1]+Distance;

                    Distance_Matrix[i][j]=Temp1>Temp2?Temp2:Temp1;
                    Distance_Matrix[i][j]=Distance_Matrix[i][j]>Temp3?Temp3:Distance_Matrix[i][j];

                }

            }

            Distance=Distance_Matrix[Length1][Length2];
        }

        double Aerfa=1-1.0*Distance/(Length1>Length2?Length1:Length2);
        System.out.println(Aerfa);
    }

}

class SimilarCos
{
    /*
     * 计算两个字符串的相似度（汉语的已经分好词的字符串），简单的余弦计算，未添权重
     */
    public static double getCosSimilar(String first, String second)
    {
        //创建向量空间模型，使用map实现，主键为词项，值为长度为2的数组，存放着对应词项在字符串中的出现次数
        Map<String, int[]> vectorSpace = new HashMap<String, int[]>();
        int[] itemCountArray = null;//为了避免频繁产生局部变量，所以将itemCountArray声明在此

        //以空格为分隔符，分解字符串
        String strArray[] = first.split(" ");
        for(int i=0; i<strArray.length; ++i)
        {
            if(vectorSpace.containsKey(strArray[i]))
                ++(vectorSpace.get(strArray[i])[0]);
            else
            {
                itemCountArray = new int[2];
                itemCountArray[0] = 1;
                itemCountArray[1] = 0;
                vectorSpace.put(strArray[i], itemCountArray);
            }
        }

        strArray = second.split(" ");
        for(int i=0; i<strArray.length; ++i)
        {
            if(vectorSpace.containsKey(strArray[i]))
                ++(vectorSpace.get(strArray[i])[1]);
            else
            {
                itemCountArray = new int[2];
                itemCountArray[0] = 0;
                itemCountArray[1] = 1;
                vectorSpace.put(strArray[i], itemCountArray);
            }
        }

        //计算相似度
        double vector1Modulo = 0.00;//向量1的模
        double vector2Modulo = 0.00;//向量2的模
        double vectorProduct = 0.00; //向量积
        Iterator iter = vectorSpace.entrySet().iterator();

        while(iter.hasNext())
        {
            Map.Entry entry = (Map.Entry)iter.next();
            itemCountArray = (int[])entry.getValue();

            vector1Modulo += itemCountArray[0]*itemCountArray[0];
            vector2Modulo += itemCountArray[1]*itemCountArray[1];

            vectorProduct += itemCountArray[0]*itemCountArray[1];
        }

        vector1Modulo = Math.sqrt(vector1Modulo);
        vector2Modulo = Math.sqrt(vector2Modulo);

        //返回相似度
        return (vectorProduct/(vector1Modulo*vector2Modulo));
    }

    /*
     *
     */
    public static void main(String args[])
    {
        String str1 = "a gold silver truck";
        String str2 = "Shipment of gold damaged in a fire";
        String str3 = "Delivery of silver arrived in a silver truck";
        String str4 = "Shipment of gold arrived in a truck";
        String str5 = "我  是  一个 中国 人";
        String str6 = "你 妈妈  是  一个 美国 人";
        String Str_1="城市 范围内 将 多个 校园网 互联 构成 城域网。多个 城域网 又 通过 路由器 与 光纤 接入 作为 国家级 或 区域 主干 网 的 广域网";
        String Str_2="城市范围内将多个校园网互联构成城域网。";
//        String Str_2="城市 范围 有 校园网,多个 局域网 通过 路由器 与 光纤 国家级 进入 家庭";
        System.out.println(SimilarCos.getCosSimilar(str1, str2));
        System.out.println(SimilarCos.getCosSimilar(str3, str4));
        System.out.println(SimilarCos.getCosSimilar(str5, str6));
        System.out.println(SimilarCos.getCosSimilar(Str_1, Str_2));

        List<String> terms1 = HanLP.extractKeyword("城市范围内将多个校园网互联构成城域网。多个城域网又通过路由器与光纤接入作为国家 级或区域主干网的广域网",10);
        List<String> terms2 =  HanLP.extractKeyword(Str_2,10);

//        List<Term> terms1 = StandardTokenizer.segment("城市范围内将多个校园网互联构成城域网。多个城域网又通过路由器与光纤接入作为国家 级或区域主干网的广域网");
//        List<Term> terms2 = StandardTokenizer.segment(Str_2);
        System.out.println(terms1);
        System.out.println(terms2);

        StringBuffer stringBuffer = new StringBuffer();
        terms1.stream().forEach(e -> {
            stringBuffer.append(e + " ");
        });


        StringBuffer stringBuffer2 = new StringBuffer();
        terms2.stream().forEach(e -> {
            stringBuffer2.append(e + " ");
        });
        System.out.println(stringBuffer.toString());
        System.out.println(stringBuffer2.toString());
        System.out.println(SimilarCos.getCosSimilar(stringBuffer.toString(), stringBuffer2.toString()));
    }
}