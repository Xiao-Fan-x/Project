package com.example.demo;

import com.example.demo.dao.click.ExamMsg;
import com.example.demo.dao.master.StudentMapper;
import com.example.demo.entity.roles.User;
import com.example.demo.util.MD5Utils;
import com.example.demo.util.RedisUtil;
import com.example.demo.util.TokenUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@MapperScan("com.example.demo.mapper")
@SpringBootTest
class DemoApplicationTests {


  @Autowired
  private ExamMsg examMsg;

  @Autowired
  private RedisUtil redisUtil;

  @Autowired
  private StudentMapper studentMapper;

  @Test
  void clickhouseTest() {
        System.out.println("clickhouse test");
        List<Map<String, Object>> a = examMsg.get();
        System.out.println(a);
//    User user = studentMapper.getById("6018203252");
//    user.setRoles(Arrays.asList(user.getRole()));
//    System.out.println(user);
  }

  @Test
  void redisTest() {
    System.out.println("redis test");
    Boolean has = redisUtil.hasKey("6018203252");
    System.out.println(has);
  }

  @Test
  void redisTest2() {
    System.out.println("redis test");
    Boolean has = redisUtil.set("6018203252", TokenUtils.createToken("6018203252"));
    System.out.println(has);
    System.out.println(redisUtil.get("6018203252"));
  }

  @Test
  void Password() throws IOException {
    String str = "123456";
    System.out.println(MD5Utils.encrypt(str));

    String s1 = "ab";
    String s2 = new String("abc");
    String s3 = s1 + "c";
    System.out.println(s3 == s2);// false
    System.out.println(s3.equals(s2));// true

//        PrintWriter printWriter = new PrintWriter(System.out);
//        printWriter.println("111");

//        Class.forName("com.clickhouse.jdbc.ClickHouseDataSource");

  }

  @Test
  public void logTest() {
    List<Map<String, Object>> a = examMsg.get();

    for (int i = 0; i <= 200; i++) {
      log.info(a.toString());
    }
  }

}
