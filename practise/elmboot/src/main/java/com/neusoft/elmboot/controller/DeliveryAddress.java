package com.neusoft.elmboot.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.service.DeliveryAddressService;
@RestController
@RequestMapping("/DeliveryAddressController")
public class DeliveryAddressController {
 @Autowired
 private DeliveryAddressService deliveryAddressService;
 
 @RequestMapping("/listDeliveryAddressByUserId")
 public List<DeliveryAddress> listDeliveryAddressByUserId(DeliveryAddress deliveryAddress) 
throws Exception{
 return deliveryAddressService.listDeliveryAddressByUserId(deliveryAddress.getUserId());
 } 
 
 @RequestMapping("/getDeliveryAddressById")
 public DeliveryAddress getDeliveryAddressById(DeliveryAddress deliveryAddress) throws
Exception{
 return deliveryAddressService.getDeliveryAddressById(deliveryAddress.getDaId());
 } 
 
 @RequestMapping("/saveDeliveryAddress")
 public int saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception{
 return deliveryAddressService.saveDeliveryAddress(deliveryAddress);
 } 
 
 @RequestMapping("/updateDeliveryAddress")
 public int updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception{
 return deliveryAddressService.updateDeliveryAddress(deliveryAddress);
 } 
 
 @RequestMapping("/removeDeliveryAddress")
 public int removeDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception{
 return deliveryAddressService.removeDeliveryAddress(deliveryAddress.getDaId());
 }
}