package com.neusoft.elm;

import com.neusoft.elm.po.Admin;
import com.neusoft.elm.view.AdminView;
import com.neusoft.elm.view.BusinessView;
import com.neusoft.elm.view.impl.AdminViewImpl;
import com.neusoft.elm.view.impl.BusinessViewImpl;

import java.util.Scanner;

public class ElmAdminEntry {
    public void work(){
        Scanner input = new Scanner(System.in);//文本扫描器
        System.out.println("---------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\t\t饿了么后台管理系统\t\t\t\t\t\t\t|");
        System.out.println("---------------------------------------------------------------------");
        AdminView adminView=new AdminViewImpl();
        BusinessView businessView=new BusinessViewImpl();

        boolean loginSuccess=false;

        //登录
        do {
            Admin admin=adminView.login();
            if(admin!=null){
                loginSuccess=true;
            }
            if (admin != null) {
                int menu = 0;
                while (menu != 5) {
                    //输出主菜单
                    System.out.println("\n========= 1.所有商家列表=2.搜索商家=3.新建商家=4.删除商家=5.退出系统=========\n");
                    System.out.println("请输入你的选择：");
                    try{
                        menu = input.nextInt();
                    }catch (Exception e){
                        System.out.println("非法输入！");
                        String a=input.next();
                        //menu = input.nextInt();
                        continue;
                    }


                    switch (menu) {
                        case 1:
                            businessView.listBusinessALL();
                            break;
                        case 2:
                            businessView.listBusiness();
                            break;
                        case 3:
                            businessView.saveBusiness();
                            break;
                        case 4:
                            businessView.removeBusiness();
                            break;
                        case 5:
                            System.out.println("----------------------欢迎下次光临饿了么后台管理系统----------------------");
                            break;
                        default:
                            System.out.println("没有这个选项！\n");
                            break;
                    }
                }

            } else {
                System.out.println("\n管理员名称或密码输入错误\n");
                System.out.println("重新登录输入y，按其他任意键");
                String str=input.next();
                if(str.equals("y")){
                   continue;
                }
                else{
                    System.out.println("----------------------欢迎下次光临饿了么后台管理系统----------------------");
                    break;
                }

            }
        }while (!loginSuccess);
    }
    public static void main(String[] args){
        new ElmAdminEntry().work();
    }
}
