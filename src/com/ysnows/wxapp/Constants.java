package com.ysnows.wxapp;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量类，用来存储各种常量
 * <p>
 * Created by lypeer on 2016/9/29.
 */
class Constants {

    //存放属性头的方法，写在这里方便后续做修改
    static final List<String> sAttributesName = new ArrayList<>();

    static {
        sAttributesName.add("bind");
        sAttributesName.add("catch");
    }

    /**
     * 存放各种需要弹出的信息的接口
     */
    interface Message {
        String
                ERROR_NOT_FOUND = "没有找到对应的文件",
                ERROR_FILE_NULL = "Can't get file , please check your permission or contact me",
                ERROR_MORE_THAN_ONE_FILE = "There exists more than one file in your project called ",
                ERROR_FILE_NOT_SUPPORT = "不支持的文件类型",
                MESSAGE_INJECT_SUCCESSFULLY = "生成成功：为你生成了 %1$d 个方法",
                MESSAGE_INJECT_NOTHING = "没有需要生成的方法",
                MESSAGE_DOWNLOAD_SUCCESS = "微信wx.d.ts文件下载成功",
                MESSAGE_INIT_TP_SUCCESSFULLY = "初始化微信page模板成功";
    }
}
