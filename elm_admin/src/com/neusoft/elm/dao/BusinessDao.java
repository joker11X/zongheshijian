package com.neusoft.elm.dao;

import com.neusoft.elm.po.Business;

import java.util.List;

public interface BusinessDao {
    public List<Business> listBusiness(String businessName,String businessAddress);
    public List<Business> listAllBusiness();
    public int saveBusiness(String businessName);//代表新生成的商家编号
    public int removeBusiness(int businessId);

    public Business getBusinessByIdByPass(Integer businessId,String password);
    public Business getBusinessById(Integer businessId);
    public int updateBusiness(Business business);
    public int updateBusinessByPassword(Integer businessId,String password);
}
