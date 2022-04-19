package com.example.demo.util;

import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MD5Utils {

    public String encrypt(String base) throws IOException {

        StringBuilder str = new StringBuilder();

        str.append(base).append("tjrac");
        String encryptStr = DigestUtils.md5DigestAsHex(str.toString().getBytes());

        return encryptStr;
    }
}
