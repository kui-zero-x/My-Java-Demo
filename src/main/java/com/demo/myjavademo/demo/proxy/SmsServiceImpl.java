package com.demo.myjavademo.demo.proxy;

public class SmsServiceImpl implements SmsService {

    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}

