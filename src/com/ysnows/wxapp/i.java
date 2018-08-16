package com.ysnows.wxapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class i {

    public static void main(String[] args) {
        connect();

        // 按指定模式在字符串查找
//        String str = "1*2*1+1";
//        Pattern pattern = Pattern.compile("^\\d+((\\+|-|\\*|/|%)\\d+)*");
//        Matcher matcher = pattern.matcher(str);
//        if (matcher.find()) {
//            //寻找数字
//            ArrayList<Integer> numbers = new ArrayList<>();
//            Matcher numMather = Pattern.compile("(\\d)+").matcher(str);
//            while (numMather.find()) {
//                String group = numMather.group();
//                numbers.add(Integer.valueOf(group));
//            }
//
//            //寻找表达式
//            ArrayList<String> symbols = new ArrayList<>();
//            Matcher symbolMather = Pattern.compile("(\\+|-|\\*|/|%)").matcher(str);
//            while (symbolMather.find()) {
//                String group = symbolMather.group();
//                symbols.add(group);
//            }
//
//        }
    }


    /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        Statement statement = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:city.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            statement = conn.createStatement();

            ResultSet res = statement.executeQuery("select * from city");
            while (res.next()) {
                String city_cn = res.getString("City_CN");
                String country_cn = res.getString("Country_CN");
                String province_cn = res.getString("Province_CN");
                String city_id = res.getString("City_ID");
                String city_cn_simple = PinyinUtil.getPinyinSimple(city_cn);
                String country_cn_simple = PinyinUtil.getPinyinSimple(country_cn);
                String province_cn_simple = PinyinUtil.getPinyinSimple(province_cn);

                PreparedStatement preparedStatement = conn.prepareStatement("update city set City_EN_SIMPLE=?, Province_EN_SIMPLE=?,Country_EN_SIMPLE=? where City_ID=?");
                preparedStatement.setString(1, city_cn_simple);
                preparedStatement.setString(2, province_cn_simple);
                preparedStatement.setString(3, country_cn_simple);
                preparedStatement.setString(4, city_id);
                preparedStatement.executeUpdate();

                System.out.println(city_cn);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}

