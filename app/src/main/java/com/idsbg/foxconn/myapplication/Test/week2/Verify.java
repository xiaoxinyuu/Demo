package com.idsbg.foxconn.myapplication.Test.week2;

import java.util.regex.Pattern;

public class Verify implements Num {
    @Override
    public boolean handleNum(String num) {
        //正则表达式
        String pattern = "(^[ACDEFGHJMPSTUVWX][0-9]{7,8}$)|(^(?![0-9]+$)(?![A-Z]+$)[0-9A-Z]{7}$)|(^[1-9][0-9]{3,5}$)";


        boolean isMatch = Pattern.matches(pattern, num);
        return isMatch;
    }

    public static void main(String[] args) {
        Num verify = new Verify();

        System.out.println(verify.handleNum("c3416446"));
        System.out.println(verify.handleNum("1000000"));
        System.out.println(verify.handleNum("FSD123456"));
        System.out.println(verify.handleNum("SDFG123"));
        System.out.println(verify.handleNum("F12345678"));
    }
}
