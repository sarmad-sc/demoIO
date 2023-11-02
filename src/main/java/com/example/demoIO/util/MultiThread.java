package com.example.demoIO.util;

public class MultiThread implements Runnable {

    int no;

    public MultiThread(int i) {
        this.no = i;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println("this is thread no " + no);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
