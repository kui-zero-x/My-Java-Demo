package com.books.books.Spring_In_Action.core.di;

import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements Player {

    @Override
    public void play() {
        System.out.println("开始播放。。。。。。");
    }
}
