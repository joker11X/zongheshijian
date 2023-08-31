package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.util.DBUtil;

import java.sql.*;

//Dao层实现类
public class AdminDaoImpl implements AdminDao {
    private Connection con=null;
    private  PreparedStatement pst=null;
    private ResultSet rs=null;
    @Override
    public Admin getAdminByNamePass(String adminName, String password) {
        Admin admin = null;//查出来了返回一个对象，没查出来返回null，通过其来判断是否存在数据
        String sql = "select * from admin where adminName=? and password=?";
        try{
            con = DBUtil.getGetConnection();
            pst=con.prepareStatement(sql);
            pst.setString(1,adminName);
            pst.setString(2,password);
            rs=pst.executeQuery();//结果集
            while(rs.next()){
                //查询出来数据时返回的才是对象
                admin=new Admin();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setPassword(rs.getString("password"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pst,con);
        }
        return admin;
    }
}
