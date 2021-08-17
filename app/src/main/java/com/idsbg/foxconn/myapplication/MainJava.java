package com.idsbg.foxconn.myapplication;

public class MainJava {
    public static void main(String[] args) {
        int[] arrs=new int[10];
        //放入10个[0,100)范围内的整数
        for(int i=0;i<arrs.length;i++){
            arrs[i]=(int)(Math.random()*100);
        }
        //找出小于60的整数
        for (int i=0;i<arrs.length;i++){
            if(arrs[i]<60){
                System.out.println(arrs[i]);
            }
        }
    }
}
