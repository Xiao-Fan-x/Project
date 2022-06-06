package com.example.demo;

import java.io.*;

public class CodeRunntimeTest {
    static String codePath = "code/";

    static String pycode = "print(\"Hello, World!\");";

    public static void main(String[] args) throws IOException, InterruptedException {

        // String javCode = "class Main \{"
        //     +"public static void main\(String\[\] args) {"
        //     +    "System.out.println("hello !!!");"
        //     +"}}";
        // System.out.println("hello world!");

        // File file = new File(codePath + File.separator + "hello.cpp");
        // File file = new File(codePath + File.separator + "demo.py");

        // try (OutputStream outputStream = new FileOutputStream(file)) {
        // outputStream.write(pycode.getBytes());
        // }
        Runtime runtime = Runtime.getRuntime();
        Process process = null;

        process = runtime.exec("javac code" + File.separator + "Main.java");
        if(process.waitFor()==0){
            process = runtime.exec("java code.Main");
        }

        // process = runtime.exec("java code" + File.separator + "Main");
        // Process process = runtime.exec("python code/demo.py");
        // Process process = runtime.exec("g++ -o code/hello.cpp");

        String outStr = getStreamStr(process.getInputStream());
        String errStr = getStreamStr(process.getErrorStream());

        int exitValue = process.waitFor(); // 退出值 0 为正常，其他为异常
        // process.wait();

        System.out.println("exitValue: " + exitValue);
        System.out.println("outStr: " + outStr);
        System.out.println("errStr: " + errStr);
        process.destroy();
    }

    private static String getStreamStr(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }

}
