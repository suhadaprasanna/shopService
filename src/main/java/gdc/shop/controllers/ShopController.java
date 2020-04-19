/**
 * 
 */
package gdc.shop.controllers;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdc.shop.controllers.response.ResponseGeneratorFactory;
import gdc.shop.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.shop.common.form.RequestParamForm;
import gdc.shop.common.form.ShopContactForm;
import gdc.shop.common.form.ShopForm;
import gdc.shop.service.ShopService;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value="/shop")
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	ResponseGeneratorFactory resFactory;
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value= {"","/"})
	public String shop() {
		return "shop controller";
	}
	
	@PostMapping(value= {"/add"})
	public Object addShop(@ModelAttribute ShopForm form, HttpServletRequest req) {
		logger.debug("------>>start addShop<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shopForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.shopService.addShop(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_ADD,req);
		logger.debug("------>>end addShop<<------");
		return res;
	}
	
	@PostMapping(value= {"/update"})
	public Object updateShop(@ModelAttribute ShopForm form, HttpServletRequest req) {
		logger.debug("------>>start updateShop<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shopForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		logger.debug("------>>end updateShop<<------");
		return dataTrans;
	}
	
	@PostMapping(value= {"/contact/add"})
	public Object addShopContact(@ModelAttribute ShopContactForm form, HttpServletRequest req) {
		logger.debug("------>>start addShopContact<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shopContactForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.shopService.addShopContact(dataTrans);
		logger.debug("------>>end addShopContact<<------");
		return dataTrans;
	}
	
	@PostMapping(value= {"/contact/get/id/{id}"})
	public Object addShopContact(@PathVariable long id) {
		logger.debug("------>>start addShopContact<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("id", id);
		dataTrans = this.shopService.getShopContactByid(dataTrans);
		logger.debug("------>>end addShopContact<<------");
		return dataTrans;
	}
	
	@PostMapping(value= {"/get/id/{id}"})
	public Object getShop(@ModelAttribute("form") ShopForm form,HttpServletRequest req) {
		logger.debug("------>>start getShop<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput(Key.FORM, form);
		dataTrans = this.shopService.getShopById(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_GET,req);
		logger.debug("------>>end getShop<<------");
		return res;
	}
	
	@PostMapping(value= {"/get"})
	public Object getShopList(@ModelAttribute("form") ShopForm form,HttpServletRequest req) {
		logger.debug("------>>start getShopList<<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
		dataTrans.addInput(Key.FORM, form);
		dataTrans = this.shopService.getShops(dataTrans);
		}catch(Exception e) {
			logger.error("------>>Error :",e);
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_GET_LIST,req);
		logger.debug("------>>end getShopList<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/user/id/{user_id}"})
	public Object getByUserId(@PathVariable long user_id,HttpServletRequest req) {
		logger.debug("------>>start getByUserId<<------");
		HashMap<String,Object> reqParam = new HashMap<>();
		reqParam.put("user_id", user_id);
		
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("user_id", user_id);
		dataTrans.addInput("reqParam", reqParam);
		
		dataTrans = this.shopService.getByUserId(dataTrans);
		
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_GET_LIST,req);
		logger.debug("------>>end getByUserId<<------");
		return res;
	}
	
	@PostMapping(value="/enable_disable")
	public Object shopEnableDisable(@ModelAttribute("shopForm") ShopForm form, HttpServletRequest req) {
		logger.debug("------>> start itemEnableDisable() <<------");
		DataTransfer dataTrans = new DataTransfer();
		try {
			dataTrans.addInput(Key.FORM, form);
			dataTrans = this.shopService.shopEnableDisable(dataTrans);
		} catch (Exception e) {
			logger.error("------>> Error :",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_GET,req);
		logger.debug("------>> end itemEnableDisable() <<------");
		return res;
	}
}
