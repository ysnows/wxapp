package com.ysnows.wxapp;

import java.util.List;

class AppJson {


    public List<String> pages;
    public WindowBean window;
    public TabBarBean tabBar;
    public NetworkTimeoutBean networkTimeout;

    public static class WindowBean {
        public String backgroundTextStyle;
        public String backgroundColor;
        public String navigationBarBackgroundColor;
        public String navigationBarTitleText;
        public String navigationBarTextStyle;
        public boolean enablePullDownRefresh;
    }

    public static class TabBarBean {
        public String color;
        public String selectedColor;
        public String borderStyle;
        public String backgroundColor;
        public List<ListBean> list;

        public static class ListBean {
            public String pagePath;
            public String iconPath;
            public String selectedIconPath;
            public String text;
        }
    }

    public static class NetworkTimeoutBean {
        public int request;
    }
}
