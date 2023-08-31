package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusinessDaoImpl implements BusinessDao {
    private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;

    @Override
    public int saveBusiness(String businessName) {
        int businessId=0;
        String sql ="insert into business(businessName,password) values(?,'123')";//主键自动生成
        try{
            con = DBUtil.getGetConnection();
            //设置返回自增长列值
            pst=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1,businessName);
            pst.executeUpdate();
            //专门获取自增长列值（一行一列）
            rs=pst.getGeneratedKeys();
            if(rs.next()){
                businessId=rs.getInt(1);//????
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pst,con);
        }
        return businessId;
    }

    @Override
    public List<Business> listBusiness(String businessName,String businessAddress) {
        System.out.println("是否开启精确搜索（y/n）");
        Scanner input=new Scanner(System.in);
        boolean whe=false;
        String a=input.next();
        if(a.equals("y")) whe=true;
        else if (a.equals("n")) {
            whe=false;
        } else {
            System.out.println("非法输入！默认开启精确搜索！");
            whe=true;
            System.out.println(a);
        }
        List<Business> list=new ArrayList<>();//查出来了返回一个对象，没查出来返回null，通过其来判断是否存在数据
        StringBuffer sql = new StringBuffer("select * from business ");
        if(businessName!=null&&!businessName.equals("")){//标准的判断一个字符串类型是否为空
            if(whe){
                sql.append("where businessName like '%"+businessName+"%' ");
            }
            else if(!whe){
                sql.append("where ");
                for(int i=0;i<businessName.length();i++){
                    if(i!=0){
                        sql.append(" or");
                    }
                    sql.append(" businessName like '%"+businessName.charAt(i)+"%' ");
                }
            }
        }
        if(businessAddress!=null&&!businessAddress.equals("")){//标准的判断一个字符串类型是否为空
            boolean hasName=true;
            if(!(businessName!=null&&!businessName.equals(""))){
                sql.append(" where ");
                hasName=false;
            }
            if(whe){
                if(hasName)sql.append(" and ");
                sql.append(" businessAddress like '%"+businessAddress+"%' ");
            } else if (!whe) {
                    for(int i=0;i<businessAddress.length();i++){
                        if(!hasName&&i!=0){
                            sql.append(" or");
                        }else if(hasName){
                            sql.append(" or");
                        }
                        sql.append(" businessAddress like '%"+businessAddress.charAt(i)+"%' ");
                    }
            }
            //System.out.println(sql);
        }
        try{
            con = DBUtil.getGetConnection();
            pst=con.prepareStatement(sql.toString());
            rs=pst.executeQuery();//结果集
            while(rs.next()){
                //查询出来数据时返回的才是对象
                Business business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                list.add(business);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pst,con);
        }
        return list;
    }

    @Override
    public int removeBusiness(int businessId) {
        int result=0;
        String delFootSql ="delete from food where businessId=? ";
        String delBusinessSql ="delete from business where businessId=? ";
        try{
            con = DBUtil.getGetConnection();
            con.setAutoCommit(false);//?开启一个事务

            pst=con.prepareStatement(delFootSql);
            pst.setInt(1,businessId);
            pst.executeUpdate();

            pst=con.prepareStatement(delBusinessSql);
            pst.setInt(1,businessId);
            result=pst.executeUpdate();

            //int a=10/0;--测试错误

            con.commit();//提交事务

        }catch (Exception e){
            result=0;
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtil.close(null,pst,con);//表明这个方法根本没用到rs
        }
        return result;
    }

    @Override
    public Business getBusinessByIdByPass(Integer businessId, String password) {
        Business business=null;//查出来了返回一个对象，没查出来返回null，通过其来判断是否存在数据
        String sql = "select * from business where businessId=? and password = ?";
        try{
            con = DBUtil.getGetConnection();
            pst=con.prepareStatement(sql.toString());
            pst.setInt(1,businessId);
            pst.setString(2,password);
            rs=pst.executeQuery();//结果集
            while(rs.next()){
                //查询出来数据时返回的才是对象
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pst,con);
        }
        return business;
    }

    @Override
    public Business getBusinessById(Integer businessId) {
        Business business=null;//查出来了返回一个对象，没查出来返回null，通过其来判断是否存在数据
        String sql = "select * from business where businessId=?";
        try{
            con = DBUtil.getGetConnection();
            pst=con.prepareStatement(sql.toString());
            pst.setInt(1,businessId);
            rs=pst.executeQuery();//结果集
            while(rs.next()){
                //查询出来数据时返回的才是对象
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pst,con);
        }
        return business;
    }

    @Override
    public int updateBusiness(Business business) {
        int result=0;
        String sql ="update business set businessName=? ,businessAddress=?,businessExplain =?,starPrice=? ,deliveryPrice=? " +
                "where businessId=?";
        try{
            con = DBUtil.getGetConnection();
            pst=con.prepareStatement(sql);
            pst.setString(1,business.getBusinessName());
            pst.setString(2,business.getBusinessAddress());
            pst.setString(3, business.getBusinessExplain());
            pst.setDouble(4,business.getStarPrice());
            pst.setDouble(5,business.getDeliveryPrice());
            pst.setInt(6,business.getBusinessId());
            result=pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(null,pst,con);//表明这个方法根本没用到rs
        }
        return result;
    }

    @Override
    public int updateBusinessByPassword(Integer businessId, String password) {
        int result=0;
        String sql ="update business set password=? where businessId=?";
        try{
            con = DBUtil.getGetConnection();
            pst=con.prepareStatement(sql);
            pst.setString(1,password);
            pst.setInt(2,businessId);
            result=pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(null,pst,con);//表明这个方法根本没用到rs
        }
        return result;
    }

    @Override
    public List<Business> listAllBusiness() {
        List<Business> list=new ArrayList<>();
        String sql=" select * from business where 1=1";
        try{
            con = DBUtil.getGetConnection();
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();//结果集
            while(rs.next()){
                //查询出来数据时返回的才是对象
                Business business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                list.add(business);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pst,con);//表明这个方法根本没用到rs
        }
        return list;
    }
}
