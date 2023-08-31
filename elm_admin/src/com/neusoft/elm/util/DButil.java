package com.neusoft.elm.util;
import java.sql.*;
//jdbc工具类
public class DBUtil {
    //声明一个静态常量字符串，里面放数据库连接字符串
    //数据库连接需要的四个常量
    private  static final  String URL = "jdbc:mysql://localhost:3306/elm_admin?characterEncoding=utf-8";
    private  static final  String DRIVER = "com.mysql.jdbc.Driver";
    private  static final  String USERNAME = "root";
    private  static final  String PASSWORD = "Tianjin1895";
    //获取Connection
    public static Connection getGetConnection() {
        Connection con =null;
        try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    //关闭资源
    //声明静态资源要谨慎
    public static void close(ResultSet rs, PreparedStatement pst, Connection con) {

        if(rs!=null){
            try {
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            rs = null;
        }
        if(pst!=null){
            try {
                pst.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            pst = null;
        }
        if(con!=null){
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            con = null;
        }
    }

}
