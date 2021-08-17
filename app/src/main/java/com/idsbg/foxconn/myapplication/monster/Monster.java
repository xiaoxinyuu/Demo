package com.idsbg.foxconn.myapplication.monster;

import java.util.ArrayList;
import java.util.List;

public class Monster {
    public static final int TYPE_BOMB=1;
    public static final int TYPE_FLY=2;
    public static final int TYPE_MAN=3;
    //定义怪物类型的成员变量
    private int type=TYPE_BOMB;
    //定义怪物x，y坐标的成员变量
    private int x=0;
    private int y=0;
    //定义怪物是否已经死亡的旗标
    private boolean isDie=false;
    private int startX=0;
    private int startY=0;
    private int endX=0;
    private int endY=0;
    //该变量用于控制怪物的刷新速度
    int drawCount=0;
    //该变量用于控制动画刷新的速度
    private int drawIndex=0;
    //用于记录死亡动画，只绘制一次，不需要重复绘制
    //每当怪物死亡时，该变量会被初始化为等于死亡动画的总帧数
    //每当死亡动画帧播放完成时，该变量的值变为 0
    private int dieMaxDrawCount=Integer.MAX_VALUE;
    //定义怪物射出的子弹
    private List<Bullet> bulletList=new ArrayList<>();
    public Monster(int type){
        this.type=type;

    }
}
