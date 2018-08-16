package com.ysnows.wxapp;

import com.github.promeg.pinyinhelper.Pinyin;

public class PinyinUtil {

    public static String getPinyin(String str) {
        String pinyin = Pinyin.toPinyin(str, "");
        return pinyin.toLowerCase().replaceAll(" ", "");
    }

    public static String getPinyinSimple(String str) {
        String pinyin = Pinyin.toPinyin(str, ",");
        String[] strings = pinyin.split(",");

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            res.append(string.charAt(0));
        }
        return res.toString().toLowerCase().replaceAll(" ", "");
    }
}
