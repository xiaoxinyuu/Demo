package com.idsbg.foxconn.myapplication.socket;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreePrint100 {

    //代表你需要几个线程
    static  final int threadNum = 5;
    //代表你要打印哪个范围内的数字
    static  final int maxNum = 100;
    //代表你要从几开始打印
    static volatile int num = 0;

    static  ReentrantLock lock = new ReentrantLock();

    static  Condition[] conditions = new Condition[threadNum];

    static {
        for(int i = 0;i < threadNum;i++){
            conditions[i] = lock.newCondition();
        }
    }

    public static void main(String[] args) {
        for(int i = 0;i < threadNum;i++){
            new Thread(new roundPrint(i)).start();
        }
    }

    static class roundPrint implements Runnable{
        final  int index;
        public roundPrint(int index){
            this.index = index;
        }
        @Override
        public void run() {
            while (num <= maxNum){
                lock.lock();
                //不符合条件先wait，然后等其他线程唤醒
                if(num % threadNum != index && num <= maxNum){
                    try {
                        conditions[index].await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 符合了就判断当前数是否小于当前数
                if(num <= maxNum)
                    System.out.println(Thread.currentThread().getName() +" "+(num++));
                // 唤醒下一个线程
                conditions[(index+1)%threadNum].signal();
                lock.unlock();
            }
        }
    }
}
