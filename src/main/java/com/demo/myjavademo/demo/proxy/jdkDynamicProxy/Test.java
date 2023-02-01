package com.demo.myjavademo.demo.proxy.jdkDynamicProxy;

import com.demo.myjavademo.demo.proxy.SmsService;
import com.demo.myjavademo.demo.proxy.SmsServiceImpl;

public class Test {

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
