package com.neusoft.elm;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.view.BusinessView;
import com.neusoft.elm.view.FoodView;
import com.neusoft.elm.view.impl.BusinessViewImpl;
import com.neusoft.elm.view.impl.FoodViewImpl;

import java.util.Scanner;

public class ElmBusinessEntry {
    public void work(){
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\t\t饿了么后台管理系统\t\t\t\t\t\t\t|");
        System.out.println("---------------------------------------------------------------------");

        BusinessView businessView=new BusinessViewImpl();
        Business business=businessView.login();
        //商家登陆
        do{
            if(business!=null){
                int menu = 0;
                while(menu!=5){
                    //先输出一级菜单
                    System.out.println("\n=======一级菜单（商家管理）1.查看商家信息=2.修改商家信息=3.更新密码=4.所属商品管理=5.退出系统=======\n");
                    System.out.println("请输入你的选择");
                    menu=input.nextInt();

                    switch(menu){
                        case 1:
                            businessView.showBusiness(business.getBusinessId());//显示即时数据
                            break;
                        case 2:
                            businessView.editBusiness(business.getBusinessId());
                            break;
                        case 3:
                            businessView.updateBusinessByPassword(business.getBusinessId());
                            break;
                        case 4:
                            foodManager(business.getBusinessId());
                            break;
                        case 5:
                            System.out.println("----------------------欢迎下次光临饿了么后台管理系统----------------------");
                            break;
                        default:
                            System.out.println("没有这个选项！\n");
                            break;
                    }
                }
            }else{
                System.out.println("商家编号或密码输入错误！");
                System.out.println("是否重新登录(y/n)");
                String str=input.next();
                if(str.equals("n")){
                    System.out.println("----------------------欢迎下次光临饿了么后台管理系统----------------------");
                    break;
                }
                else if(str.equals("y")){
                    business=businessView.login();
                }
                else{
                    System.out.println("非法输入，程序退出！");
                    System.out.println("----------------------欢迎下次光临饿了么后台管理系统----------------------");
                    break;
                }
            }
        }while (business==null);
    }
    private void foodManager(int businessId){
        Scanner input = new Scanner(System.in);

        FoodView foodView=new FoodViewImpl();

        int menu = 0;
        while(menu!=5){
            //先输出一级菜单
            System.out.println("\n=======二级菜单（食品管理）1.查看食品列表=2.新增食品=3.修改食品=4.删除食品=5.返回一级菜单===========\n");
            System.out.println("请输入你的选择");
            menu=input.nextInt();

            switch(menu){
                case 1:
                    foodView.showFoodList(businessId);
                    break;
                case 2:
                    foodView.saveFood(businessId);
                    break;
                case 3:
                    foodView.updateFood(businessId);
                    break;
                case 4:
                    foodView.removeFood(businessId);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("没有这个选项！\n");
                    break;
            }
        }

    }
    public static void main(String[] args){
        new ElmBusinessEntry().work();
    }
}
