package com.idsbg.foxconn.myapplication.socket;

import java.util.ArrayList;

public class Test {
    public ArrayList<Worker> orderByAge(ArrayList<Worker> workers){
        //按年龄进行冒泡排序
        for(int i=0;i<workers.size()-1;i++){
            for(int j=0;j<workers.size()-i-1;j++){
                if(workers.get(j).getAge()>workers.get(j+1).getAge()){
                    Worker worker=workers.get(j);
                    workers.set(j,workers.get(j+1));
                    workers.set(j+1,worker);
                }
            }
        }
        return workers;
    }
    public static void main(String[] args) {
        ArrayList<Worker> arrayList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Worker worker=new Worker();
            worker.setName("A"+Math.random()*100);
            worker.setAge((int)(Math.random()*10+30));
            //2000-4000元之间
            worker.setSalary(Math.random()*2000+2000);
            arrayList.add(worker);
        }
        Test test=new Test();
        test.orderByAge(arrayList);
        for(int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(i).getAge());
        }
    }
}
