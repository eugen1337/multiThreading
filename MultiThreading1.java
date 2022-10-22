package com.company;

class Message {
    private String msg;
    public Message(String str){
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String str) {
        this.msg=str;
    }

}

class Mythread implements Runnable{

    private Message msg;

    private int number;

    public Mythread(Message m, int number){
        this.msg = m;
        this.number = number;
    }

    @Override
    public void run() {
        synchronized (msg) {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(500);
                    msg.notify();
                    msg.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class MultiThreading1 {
    public static void main(String[] args) {
        Message msg = new Message("Поток");
        Mythread thread1 = new Mythread(msg, 1);
        new Thread(thread1,"Поток1").start();

        Mythread thread2 = new Mythread(msg, 2);
        new Thread(thread2,"Поток2").start();
    }
}
