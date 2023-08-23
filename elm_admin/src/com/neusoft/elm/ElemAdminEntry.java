package com.neusoft.elm;

import java.util.Scanner;

public class ElemAdminEntry {

	public void work() {
		//登录
		Scanner input = new Scanner(System.in); //输入流
		
		//输出标题
		System.out.println("---------------------------------------------------------");
		System.out.println("|\t\t\t 饿了么后台管理系统 \t\t\t|");
		System.out.println("---------------------------------------------------------");
		if(true) { //success
			int menu = 0;//功能序号
			while(menu != 5) {
				//输出主菜单
				System.out.println("\n====== 1.所有商家列表 = 2.搜索商家 = 3.新建商家 = 4.删除商家 = 5.退出系统 ======\n");
				System.out.println("请输入序号选择功能：");
				menu = input.nextInt();
				switch(menu) { //功能选项
				      case 1:
				    	  System.out.println("所有商家列表");
				      break;
				      case 2:
				    	  System.out.println("搜索商家");
				      break;
				      case 3:
				    	  System.out.println("新建商家");
				      break;
				      case 4:
				    	  System.out.println("删除商家");
				      break;
				      case 5:
				    	  System.out.println("-----------------欢迎下次使用饿了么后台管理系统 -----------------");
				      break;
				     default:
				    	 System.out.println("��Ǹ�����޴�ѡ�\n");
					      break;
				}
			}
			
			//只有在用户选择选项5时才退出系统，选择其余功能完成后仍要继续留在系统所以使用循环
		}
		else {//fail
			System.out.println("\n抱歉！暂无此功能\n");//没有该选项时显示
		}
	}
	public static void main(String[] args) {
	 new ElemAdminEntry().work();
 }
}
 
 
 
 
 
 
 
 