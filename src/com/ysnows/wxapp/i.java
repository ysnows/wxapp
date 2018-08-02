package com.ysnows.wxapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class i {

    public static void main(String[] args) {
        // 按指定模式在字符串查找
        String content = "var app = getApp(), $ = require(\"../../utils/util.js\"), sliderWidth = 96;\n" +
                "var WxParse = require('../../utils/wxParse/wxParse.js');\n" +
                "Page({\n" +
                "    data: {\n" +
                "        isdata: true,\n" +
                "    },\n" +
                "\n" +
                "\n" +
                "    onLoad: function (e) {\n" +
                "        var that = this;\n" +
                "        var aid = e.aid;\n" +
                "        if (aid === undefined) {\n" +
                "            $.showtips(\"参数缺失\")\n" +
                "        } else {\n" +
                "\n" +
                "            // var title=\"\";\n" +
                "            // if(aid==7){\n" +
                "            //     title=\"商城简介\"\n" +
                "            // }else if(id==8){\n" +
                "            //     title=\"使用指南\"\n" +
                "            // }else if(id==9){\n" +
                "            //     title=\"售后服务\"\n" +
                "            // }else if(id==10){\n" +
                "            //     title=\"注册协议\"\n" +
                "            // }else if(id==12){\n" +
                "            //     title=\"置换协议\"\n" +
                "            // }else if(id==13){\n" +
                "            //     title=\"推广规则\"\n" +
                "            // }\n" +
                "            console.log(\"000\")\n" +
                "            $.loading();\n" +
                "            $.AJAX(app.globalData.API_ARTICLE + '?aid=' + aid, 'GET', {}, function (data) {\n" +
                "                wx.setNavigationBarTitle({\n" +
                "                    title: data.datas.article_title\n" +
                "                })\n" +
                "                if (data.article_content !== \"\") {\n" +
                "                    var content = data.datas.article_content;\n" +
                "                    that.setData({\n" +
                "                        article: data.datas,\n" +
                "                    });\n" +
                "\n" +
                "                    console.log(\"999\")\n" +
                "                    WxParse.wxParse('content', 'html', content, that, 5);\n" +
                "                    $.hideloading();\n" +
                "                    console.log(\"111\")\n" +
                "                } else {\n" +
                "                    that.setData({\n" +
                "                        isdata: false\n" +
                "                    })\n" +
                "                    $.hideloading();\n" +
                "\n" +
                "                }\n" +
                "            })\n" +
                "        }\n" +
                "    }\n" +
                "});";



        int index = content.lastIndexOf("}");

        String substring = content.substring(0, index);


        int i = substring.lastIndexOf("}");


    }
}

