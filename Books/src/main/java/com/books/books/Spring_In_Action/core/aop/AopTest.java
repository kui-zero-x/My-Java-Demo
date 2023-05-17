package com.books.books.Spring_In_Action.core.aop;

import com.books.books.Spring_In_Action.core.di.CDPlayer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AopTest {

    @Autowired
    private CDPlayer cdPlayer;

    @Test
    public void test1() {
        cdPlayer.play();
    }
}
