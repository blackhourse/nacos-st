package cn.boot.st.web.utils;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomUtils {
    public static final int W = 10000;
    public static final int Q = 1000;

    public static String getRandomNumber(int length){
        return RandomStringUtils.randomNumeric(length);
    }

    public static int range(int min, int max){
        Random random = new Random();
        return (random.nextInt(max) % (max - min + 1) + min);
    }

}
