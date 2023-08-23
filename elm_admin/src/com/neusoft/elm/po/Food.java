package com.neusoft.elm.po;

public class Food {
	 private Integer foodId;
	 private String foodName;
	 private String foodExplain;
	 private Double foodPrice;
	 private Integer businessId;

	 @Override
	 public String toString() {//直接输出对象就可以输出信息了
	 return "\n食品编号："+this.foodId+
	 "\n食品名称："+this.foodName+
	 "\n食品介绍："+this.foodExplain+
	 "\n食品价格："+this.foodPrice+
	 "\n所属商家："+this.businessId;
	 }
}
