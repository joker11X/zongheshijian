package com.neusoft.elm.dao;

import com.neusoft.elm.po.Admin;

public interface AdminDao {
    //管理员名称有唯一约束
    //获取对象用get开头
    public Admin getAdminByNamePass(String adminName, String password);
}
