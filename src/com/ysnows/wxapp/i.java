package com.ysnows.wxapp;

class i {

    public static void main(String[] args) {
        // 按指定模式在字符串查找

        String name = "a.js";
        int lastIndexOf = name.lastIndexOf(".");
        String substring = name.substring(0, lastIndexOf);

        String newFileName = "hello";
        String replace = name.replace(substring, newFileName);
    }


}

