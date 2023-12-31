package com.neusoft.elm.view.impl;

import com.neusoft.elm.dao.FoodDao;
import com.neusoft.elm.dao.impl.FoodDaoImpl;
import com.neusoft.elm.po.Food;
import com.neusoft.elm.view.FoodView;

import java.util.List;
import java.util.Scanner;

public class FoodViewImpl implements FoodView {
    private Scanner input=new Scanner(System.in);
    @Override
    public List<Food> showFoodList(Integer businessId) {
        FoodDao dao=new FoodDaoImpl();
        List<Food> list=dao.listFoodByBusinessId(businessId);
        if(list.size()==0){
            System.out.println("没有任何食品！");
        }else {
            System.out.println("食品编号\t食品名称\t食品介绍\t食品价格");
            for(Food food:list){
                System.out.println(food.getFoodId()+"\t\t"+food.getFoodName()+"\t"+food.getFoodExplain()+"\t\t"+food.getFoodPrice());
            }
        }
        return list;
    }

    @Override
    public void saveFood(Integer businessId) {
        Food food=new Food();
        System.out.println("请输入食品名称：");
        food.setFoodName(input.next());
        System.out.println("请输入食品介绍：");
        food.setFoodExplain(input.next());
        System.out.println("请输入食品价格：");
        try{
            food.setFoodPrice(input.nextDouble());
        }catch (Exception e){
            System.out.println("食品价格输入出现错误！");
        }

        food.setBusinessId(businessId);

        FoodDao dao=new FoodDaoImpl();
        int result=dao.saveFood(food);
        if(result>0){
            System.out.println("\n新增食品成功！");
        }else{
            System.out.println("\n新增食品失败！");
        }
    }

    @Override
    public void removeFood(Integer businessId) {
        FoodDao dao=new FoodDaoImpl();
        List<Food> list=showFoodList(businessId);
        //判断有没有食品
        if(list.size()==0){
            System.out.println("没有任何食品！");
        }else {//可能误删其他商家的食品
            System.out.println("请选择要删除的食品编号：");
            int foodId=input.nextInt();
            Food food= dao.getFoodById(foodId);
            if(!food.getBusinessId().equals(businessId)){
                System.out.println("输入了错误的食品编号！");
                return;
            }
            if(food==null){
                System.out.println("该食物不存在！");
                return;
            }
            System.out.println("确认要删除吗（y/n）：");
            if(input.next().equals("y")){
                int result=dao.removeFood(foodId);
                if(result>0){
                    System.out.println("删除食品成功！");
                }else {
                    System.out.println("删除食品失败！");
                }
            }else if (!input.equals("n")) {
                System.out.println("未知输入！");
                System.out.println("删除食品失败！");
            }

        }
    }

    @Override
    public void updateFood(Integer businessId) {
        FoodDao dao=new FoodDaoImpl();
        List<Food> list=showFoodList(businessId);
        //判断有没有食品
        if(list.size()==0){
            System.out.println("没有任何食品！");
        }else {
            System.out.println("请选择要更新的食品编号：");
            int foodId=input.nextInt();
            Food food=dao.getFoodById(foodId);
            if(food==null){
                System.out.println("该食物不存在！");
                return;
            }
            if(!food.getBusinessId().equals(businessId)){
                System.out.println("输入了错误的食品编号！");
                return;
            }
            System.out.println(food);

            String inputStr="";
            System.out.println("是否更新食品名称（y/n）：");
            inputStr=input.next();
            if(inputStr.equals("y")){
                System.out.println("请输入新的食品名称：");
                food.setFoodName(input.next());
            }else if (!inputStr.equals("n")) {
                System.out.println("未知输入！");
                System.out.println("更新食品名称失败！");
                return;
            }

            System.out.println("是否更新食品介绍（y/n）：");
            inputStr=input.next();
            if(inputStr.equals("y")){
                System.out.println("请输入新的食品介绍：");
                food.setFoodExplain(input.next());
            }else if (!inputStr.equals("n")) {
                System.out.println("未知输入！");
                System.out.println("更新食品介绍失败！");
                return;
            }

            System.out.println("是否更新食品价格（y/n）：");
            inputStr=input.next();
            if(inputStr.equals("y")){
                System.out.println("请输入新的食品价格：");
                food.setFoodPrice(input.nextDouble());
            }else if (!inputStr.equals("n")) {
                System.out.println("未知输入！");
                System.out.println("更新食品价格失败！");
                return;
            }

            int result=dao.updateFood(food);
            if(result>0){
                System.out.println("修改食品成功！");
            }else {
                System.out.println("修改食品失败！");
            }
        }
    }
}
