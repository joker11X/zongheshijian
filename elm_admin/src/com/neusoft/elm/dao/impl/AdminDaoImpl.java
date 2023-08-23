package com.neusoft.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.util.DButil;



public interface AdminDaoImpl implements AdminDao{
	 private Connection con = null;
	 private PreparedStatement pst = null;
	 private ResultSet rs = null;
	 
	 public Admin getAdminByNameByPass(String adminName, String password) {
	 Admin admin = null;
	 String sql = "select * from admin where adminName=? and password=?";
	 try {
	 con = DButil.getConnection();
	 pst = con.prepareStatement(sql);
	 pst.setString(1, adminName);
	 pst.setString(2, password);
	 rs = pst.executeQuery();//执行查询
	 while(rs.next()) {
	 admin = new Admin();//可以通过判断返回值是否为空指针来判断是否查询出了数据
	 admin.setAdminId(rs.getInt("adminId"));
	 admin.setAdminName(rs.getString("adminName"));
	 admin.setPassword(rs.getString("password"));
	 }
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } finally {
	 DButil.close(rs, pst, con);//释放资源
	 }
	 return admin;
	 }
}
