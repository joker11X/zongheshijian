package com.neusoft.elmboot.controller;
import java.util.List;
@RestController
@RequestMapping( "/BusinessController" )
public class Businesscontroller {	
@Autowired
private BusinessService businessService;
@RequestMapping("/listBusinessByOrderTypeId")
public List<Business> listBusinessByOrderTypeId(Business business) throws Exception{
return businessService.listBusinessByOrderTypeId(business.getOrderTypeId());
}
@RequestMapping("/getBusinessById")
public Business getBusinessById(Business business) throws Exception{
return businessService.getBusinessById(business.getBusinessId());
}
}	
