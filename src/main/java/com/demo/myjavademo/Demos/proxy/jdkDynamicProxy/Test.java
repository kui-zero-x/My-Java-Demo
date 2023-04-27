package com.demo.myjavademo.Demos.proxy.jdkDynamicProxy;

import com.demo.myjavademo.Demos.proxy.SmsService;
import com.demo.myjavademo.Demos.proxy.SmsServiceImpl;

public class Test {

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
