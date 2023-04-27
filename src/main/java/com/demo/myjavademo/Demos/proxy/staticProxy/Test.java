package com.demo.myjavademo.Demos.proxy.staticProxy;

import com.demo.myjavademo.Demos.proxy.SmsService;
import com.demo.myjavademo.Demos.proxy.SmsServiceImpl;

public class Test {

    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}
