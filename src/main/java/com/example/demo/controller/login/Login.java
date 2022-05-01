package com.example.demo.controller.login;


import com.example.demo.dao.click.ExamMsg;
import com.example.demo.entity.roles.Student;
import com.example.demo.entity.roles.User;
import com.example.demo.service.login.LoginImpl;
import com.example.demo.util.RedisUtil;
import com.example.demo.util.Result;
import com.example.demo.util.TokenUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/login")
public class Login {

//    private static final Logger logger = LoggerFactory.getLogger(Login.class);


  @Autowired
  private ExamMsg examMsg;

  @Autowired
  private RedisUtil redisUtil;

  @Autowired
  private LoginImpl login;


  @RequestMapping(value = "/login")
  public Object login(@RequestBody User user) {
    log.info(user.toString());
    Object obj = login.login(user);

    if (obj != null) {
      Map map = new HashMap();
      map.put("token", TokenUtils.createToken(user.getUserId()));
      map.put("user", obj);
      return Result.ResultSuccess(map);
    } else {
      return Result.ResultErr(obj, "登陆失败");
    }

  }


  @RequestMapping(value = "/register")
  public Object register(@RequestBody Student student) {

    try {
      log.info("注册");
      return Result.ResultSuccess(login.register(student));
    } catch (Exception e) {
      e.printStackTrace();
      return Result.ResultErr(e.getMessage(), "failure");
    }

  }


  @RequestMapping(value = "/home2")
  void clickhouseTest() {
    System.out.println("clickhouse test");
    List<Map<String, Object>> a = examMsg.get();
    log.info(a.toString());
  }

  //    @RequestMapping(value = "/logTest")
  public void logTest() {
    List<Map<String, Object>> a = examMsg.get();

    for (int i = 0; i <= 2000; i++) {
      log.info(a.toString());
    }
  }

  @RequestMapping(value = "/home3")
  void redisTest() {
    System.out.println("redis test");
    Boolean has = redisUtil.hasKey("6018203252");
    System.out.println(has);
  }

  @ResponseBody
  @PostMapping(value = "/home4")
  void redisTest2() {
    System.out.println("redis test");

    if (redisUtil.get("number") != null) {
      redisUtil.set("number", 1);
    } else {
      Integer i = (Integer) redisUtil.get("number");
      redisUtil.set("number", "100");
    }
    System.out.println(redisUtil.get("number"));
  }
}
