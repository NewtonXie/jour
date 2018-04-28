package com.gzcb.creditcard.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObjectTest {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 1.
         */
//        final String separator = ",";
//        Arrays.asList("a","b","d").forEach(
//                (String e)-> System.out.println(e+separator)
//        );
        /**
         * 2.
         */
//        List<String> a = new ArrayList<>();
//        a.add("1");
//        a.add("2");
//        for (String temp:a
//             ) {
//            if ("1"==temp){
//                a.remove(temp);
//            }
//        }
//        System.out.println(a);
        /**
         * 3.
         */
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(20);

        ExecutorService exec = Executors.newFixedThreadPool(10);


    }
}
