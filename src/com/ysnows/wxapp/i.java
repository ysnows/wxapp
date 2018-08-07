package com.ysnows.wxapp;

class i {

    public static void main(String[] args) {
        // 按指定模式在字符串查找

        String urlStr = "https://gitee.com/ysnow/wechat_small_program_api/raw/master/wx.d.ts";

        String name = urlStr.substring(urlStr.lastIndexOf("/")+1);

        System.out.println(name);

    }


}

