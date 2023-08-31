package com.neusoft.elm.view.impl;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.dao.impl.AdminDaoImpl;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.view.AdminView;

import java.util.Scanner;
//获取用户输入的数据
//获取数据后去数据库操作数据
//根据操作结果处理反馈
public class AdminViewImpl implements AdminView {
    private Scanner input = new Scanner(System.in);
    @Override
    public Admin login() {
        System.out.println("请输入管理员名称:");
        String adminName=input.next();
        System.out.println("请输入密码:");

        String password=input.next();
        AdminDao dao=new AdminDaoImpl();
//        Admin admin = dao.getAdminByNamePass(adminName,password);
//        return admin;
        return dao.getAdminByNamePass(adminName,password);
    }
}
