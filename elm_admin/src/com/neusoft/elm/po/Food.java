package com.neusoft.elm.po;

public class Food {
    private  Integer FoodId;
    private String foodName;
    private String foodExplain;
    private Double foodPrice;
    private Integer businessId;
    @Override
    public String toString() {
        return "\n食品编号："+this.FoodId+"\n食品名称："+this.foodName+"\n食品介绍："+this.foodExplain+
                "\n食品价格："+this.foodPrice+"\n所属商家："+this.businessId;
    }
    public Integer getFoodId() {
        return FoodId;
    }

    public void setFoodId(Integer foodId) {
        FoodId = foodId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getFoodExplain() {
        return foodExplain;
    }

    public void setFoodExplain(String foodExplain) {
        this.foodExplain = foodExplain;
    }

    public String getFoodName() {
        return foodName;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
