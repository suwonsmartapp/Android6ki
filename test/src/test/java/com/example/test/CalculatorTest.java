package com.example.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by junsuk on 2017. 10. 17..
 */
public class CalculatorTest {
    @Before
    public void setUp() throws Exception {
        // 초기화
    }

    @After
    public void tearDown() throws Exception {
        // 끝나고 메모리 해제 등등
    }

    @Test
    public void add() throws Exception {
        Assert.assertEquals(4, Calculator.add(2, 2));
        Assert.assertEquals(-1, Calculator.add(-2, 2));
    }

}