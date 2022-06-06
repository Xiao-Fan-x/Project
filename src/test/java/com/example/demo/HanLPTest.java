package com.example.demo;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.word.WordInfo;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class HanLPTest {

    @Test
    public void test() throws IOException {
        String document = "城市范围内将多个校园网互联构成城域网。多个城域网又通过路由器与光纤接入作为国家 级或区域主干网的广域网。";
//        WordVectorModel wordVectorModel = new WordVectorModel("src/main/resources/word.txt");
//        double similarRate = new DocVectorModel(wordVectorModel).similarity(document,"城市范围有校园网" );


////        String document = "位图图像也称光栅图、栅格图、点阵图，是通过每个像素点的颜色信息数值来反映原始图像的视觉效果。\n";
//        List<String> sentenceList = HanLP.extractSummary(document, 3);
//        System.out.println(sentenceList);
//        double similarity = CoreSynonymDictionary.similarity(document, "城市范围有校园网");

//        System.out.println(similarRate);
        List<Term> segment = HanLP.segment(document);
        System.out.println(segment);

//        List<String> strings = HanLP.extractKeyword(document, 8 );
//        System.out.println(strings);


        List<WordInfo> strings = HanLP.extractWords(document, 4);
        System.out.println(strings);

        // 分词
        List<Term> terms1 = StandardTokenizer.segment("他说的确实在理");

        StringBuffer stringBuffer = new StringBuffer();
        terms1.stream().forEach(e -> {
            stringBuffer.append(e.word + " ");
        });

        System.out.println(stringBuffer.toString());

    }
}
