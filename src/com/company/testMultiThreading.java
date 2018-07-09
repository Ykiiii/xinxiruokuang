package com.company;

class RunnableDemo implements Runnable{
    private Thread t;
    private String threadName;

    RunnableDemo(String name){
        threadName = name;
        System.out.println("Creating..." + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running..." + threadName);
        try{
            for(int i = 4;i>0;i--){
                System.out.println("Thread..." + threadName + "," + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted");
        }
        System.out.println("Thread " + threadName + " exiting...");
    }
    public void start(){
        System.out.println("Starting..." + threadName);
        if(t == null){
            t = new Thread(this,threadName);
            t.start();
        }
    }
}

public class testMultiThreading {
    public static void main(String args[]){
        System.out.println("---start-1---");
        RunnableDemo R1 = new RunnableDemo("Thread--1--");
        R1.start();
        System.out.println("---start-2---");
        RunnableDemo R2 = new RunnableDemo("Thread--2--");
        R2.start();
        System.out.println("---start-3---");
    }
}