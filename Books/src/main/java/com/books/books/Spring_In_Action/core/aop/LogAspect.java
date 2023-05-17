package com.books.books.Spring_In_Action.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.books.books.Spring_In_Action.core.di.CDPlayer.play(..))")
    public void BeforeLog() {
        System.out.println("快要开始播放了！！");
    }

    @After("execution(* com.books.books.Spring_In_Action.core.di.CDPlayer.play(..))")
    public void AfterLog() {
        System.out.println("播放结束了！！");
    }

    @AfterReturning("execution(* com.books.books.Spring_In_Action.core.di.CDPlayer.play(..))")
    public void afterReturning() {
        System.out.println("播放正常结束了！！！");
    }

    @AfterThrowing("execution(* com.books.books.Spring_In_Action.core.di.CDPlayer.play(..))")
    public void afterThrowing() {
        System.out.println("播放异常！！！");
    }

    @Around("execution(* com.books.books.Spring_In_Action.core.di.CDPlayer.play(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前。。。。。。。。。");
        jp.proceed();
        System.out.println("环绕后。。。。。。。。。");
    }
}
