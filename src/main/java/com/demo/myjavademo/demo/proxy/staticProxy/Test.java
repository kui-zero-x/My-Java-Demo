package com.demo.myjavademo.demo.proxy.staticProxy;

import com.demo.myjavademo.demo.proxy.SmsService;
import com.demo.myjavademo.demo.proxy.SmsServiceImpl;

public class Test {

    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}
