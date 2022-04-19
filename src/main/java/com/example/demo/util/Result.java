package com.example.demo.util;


import lombok.Data;

@Data
public class Result {

    private Integer code;
    private String msg;
    private Object data;


    public static Object ResultSuccess(Object obj){

        Result result = new Result();
        result.code = 200;
        result.msg = "sucessed";
        if (obj != null){
            result.data = obj;
        }
        return result;
    }


    public static Object ResultErr(Object obj,String msg){

        Result result = new Result();
        result.code = 400;
        result.msg = "Failure";
        if (obj != null){
            result.data = obj;
        }
        return result;
    }


}
