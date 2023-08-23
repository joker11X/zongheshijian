package com.neusoft.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButil {//数据库工具
	private static final String URL = "jdbc:mysql://localhost:3306/elm_admin?characterEncoding=utf-8";//连接数据库字符串 格式为：协议名+主机+端口+数据库名+使用字符集类型
	private static final String DRIVER = "com.mysql.jdbc.Driver";//路径要去掉.class
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	//获取connection
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);//加载mysql驱动
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);//驱动管理类做链接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	//关闭各种资源
	//只有通用的才能封装成静态的
	public static void close(ResultSet rs,PreparedStatement pst,Connection con) {
		if(rs != null) {//避免空指针且有些功能rs无值
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;//释放资源
		}
		if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pst = null;
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = null;
		}
	}
}
