package com.gzcb.creditcard.utils;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.*;

public class ThreadTest implements Runnable {
    int b = 100;
    synchronized void m1() throws Exception{
        b = 1000;
        Thread.sleep(500);
        System.out.println("b="+b);
    }
    synchronized void  m2() throws Exception{

        Thread.sleep(250);
        b = 2000;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService es  =  newFixedThreadPool(2);
        ThreadTest tt = new ThreadTest();

        es.submit(tt);
        tt.m2();
        System.out.println("tt.m2.b="+tt.b);
    }

    @Override
    public void run() {
            try {
                System.out.println("xxx");
                m1();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
