package com.neusoft.elm.view.impl;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.view.BusinessView;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView {
    private Scanner input = new Scanner(System.in);



    @Override
    public void listBusinessALL() {
        BusinessDao dao=new BusinessDaoImpl();
        List<Business> list=dao.listAllBusiness();
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for(Business b:list){
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+
                    b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice());
        }
    }

    @Override
    public void listBusiness() {
        //获取数据
        //首先询问
        //接受yes/no
        String businessName="";
        String businessAddress="";
        String inputStr = "";
        System.out.println("是否需要输入商家名称关键词（y/n）：");
        inputStr=input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入商家名称关键词：");
            businessName=input.next();
        }
        System.out.println("是否需要输入商家地址关键词（y/n）：");
        inputStr=input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入商家地址关键词：");
            businessAddress=input.next();
        }
        BusinessDao dao=new BusinessDaoImpl();//调用dao层
        List<Business> list=dao.listBusiness(businessName,businessAddress);
        if(list.size()==0){
            System.out.println("无符合条件商家！");
            return;
        }
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for(Business b:list){
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+
                    b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice());
        }
    }
    @Override
    public void saveBusiness() {
        System.out.println("请输入商家名称：");
        String businessName=input.next();
        BusinessDao dao=new BusinessDaoImpl();
        int businessId= dao.saveBusiness(businessName);
        if(businessId>0){
            System.out.println("新建商家成功！商家编号为："+businessId+"默认密码为：123");
        }else{
            System.out.println("新建商家失败！");
        }
    }

    @Override
    public void removeBusiness() {
        System.out.println("请输入商家编号：");
        try{
            int businessId=input.nextInt();

            //删除比较特殊
            //要求用户再次确认防止误删除
            BusinessDao dao=new BusinessDaoImpl();
            System.out.println("确认要删除吗（y/n）：");
            if(input.next().equals("y")){
                int result = dao.removeBusiness(businessId);
                if(result==1){
                    System.out.println("删除商家成功！");
                }else{
                    System.out.println("删除商家失败！");
                }
            }
        }catch (Exception e){
            String a=input.next();
            System.out.println("删除商家失败！");

        }

    }

    @Override
    public Business login() {
        System.out.println("请输入商家编号：");
        boolean whe=true;
        int businessId=0;
        while (whe) {
            try {
                //input = new Scanner(System.in);
                whe = !input.hasNextInt();     // 正确输入跳出循环条件
                businessId = input.nextInt();
            } catch (Exception e) { // TODO: handle exception
                System.out.println("输入错误请重新输入：");
                input.nextLine();        //
            }
        }
        System.out.println("请输入密码：");
        String password=input.next();

        BusinessDao dao=new BusinessDaoImpl();
        return dao.getBusinessByIdByPass(businessId,password);
    }

    @Override
    public void showBusiness(Integer businessId) {
        BusinessDao dao=new BusinessDaoImpl();
        Business business=dao.getBusinessById(businessId);
        System.out.println(business.toString());
    }

    @Override
    public void editBusiness(Integer businessId) {
        //先将商家信息查询出来显示，然后用户才能更新
        BusinessDao dao=new BusinessDaoImpl();
        Business business=dao.getBusinessById(businessId);
        System.out.println(business);

        String inputStr="";
        System.out.println("是否修改商家名称（y/n）：");
        inputStr=input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入新的商家名称");
            business.setBusinessName(input.next());
        } else if (!inputStr.equals("n")) {
            System.out.println("未知输入！");
            return;
        }
        System.out.println("是否修改商家地址（y/n）：");
        inputStr=input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入新的商家地址");
            business.setBusinessAddress(input.next());
        }else if (!inputStr.equals("n")) {
            System.out.println("未知输入！");
            return;
        }

        System.out.println("是否修改商家介绍（y/n）：");
        inputStr=input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入新的商家介绍");
            business.setBusinessExplain(input.next());
        }else if (!inputStr.equals("n")) {
            System.out.println("未知输入！");
            return;
        }

        System.out.println("是否修改起送费（y/n）：");
        inputStr=input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入新的起送费");
            try{
                business.setStarPrice(input.nextDouble());
            }catch (Exception e){
                String a=input.next();
                System.out.println("修改起送费失败！请重新修改！");
                return;
            }

        }else if (!inputStr.equals("n")) {
            System.out.println("未知输入！");
            return;
        }

        System.out.println("是否修改配送费（y/n）：");
        inputStr=input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入新的配送费");
            try{
                business.setDeliveryPrice(input.nextDouble());
            }catch (Exception e){
                String a=input.next();
                System.out.println("修改配送费失败！请重新修改！");
                return;
            }
        }else if (!inputStr.equals("n")) {
            System.out.println("未知输入！");
            return;
        }

        int result=dao.updateBusiness(business);
        if(result==1){
            System.out.println("\n修改商家信息成功\n");
        }else{
            System.out.println("\n修改商家信息失败\n");
        }
    }

    @Override
    public void updateBusinessByPassword(Integer businessId) {
         BusinessDao dao=new BusinessDaoImpl();
         Business business=dao.getBusinessById(businessId);

         System.out.println("\n请输入旧密码:");
         String oldPass = input.next();
        System.out.println("\n请输入新密码:");
        String password = input.next();
        System.out.println("\n请再次输入新密码:");
        String beginPassword = input.next();

        if(!business.getPassword().equals(oldPass)){
            System.out.println("\n旧密码输入错误!");
        } else if (!password.equals(beginPassword)) {
            System.out.println("\n两次输入密码不一致!");
        }else{
            int result=dao.updateBusinessByPassword(businessId,password);
            if(result>0){
                System.out.println("\n密码修改成功!");
            }else {
                System.out.println("\n密码修改失败!");
            }
        }
    }
}
