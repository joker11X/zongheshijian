package com.neusoft.elm.dao;

public interface AdminDao {//需要主要管理员名称有唯一约束
	public Admin getAdminByNameByPass(String adminName,String password);
}
