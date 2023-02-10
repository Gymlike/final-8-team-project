package com.team.final8teamproject.backjoon;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class TestBack {

    /**
     * 8 -> 4 4 = 16
     * 4 4 -> 2 2 /2 2 = 4/4 = 8
     * 2 2 2 2 -> 1 1 1 1 = 4
     * 16+8 +4 = 28
     * 6 -> 3 3  = 9
     * 나눌때 어떻게 보관할것인가. 아니면 계산식이 필요함
     * 보관할것이라면 10의 9승일때 엄청나게 나눠질수있으니 계산식을 짜는게 좋음
     * 8 -> 28
     * 6 -> 15
     * 5 -> 10
     * 4 -> 6
     * 3 -> 3
     * 2 -> 1
     * 1->0
     */
    @Test
    public void Test(){
        //given
        long test1 = 5;
        long test2 = 8;
        //when
        long result1 = test1 * (test1 - 1) / 2;
        long result2 = test2 * (test2 - 1) / 2;
        //then
        assertEquals(result1, 10);
        assertEquals(result2, 28);
    }
}
