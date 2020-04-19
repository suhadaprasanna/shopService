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

import gdc.shop.common.form.RequestParamForm;
import gdc.shop.common.form.ShopForm;
import gdc.shop.controllers.response.ResponseGeneratorFactory;
import gdc.shop.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.shop.service.UserService;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value="/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	ResponseGeneratorFactory resFactory;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {"","/"})
	public String shop() {
		return "shop controller (user)";
	}
	
	@PostMapping(value= {"/add"})
	public Object addUser(@RequestBody ShopForm form, HttpServletRequest req) {
		logger.debug("------>>start addUser<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shopForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		//dataTrans = this.shopService.addShop(dataTrans);
		//Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_ADD,req);
		logger.debug("------>>end addUser<<------");
		return form;
	}
	
	@PostMapping(value= {"/update"})
	public Object updateUser(@RequestBody ShopForm form, HttpServletRequest req) {
		logger.debug("------>>start updateUser<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shopForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		//dataTrans = this.shopService.addShop(dataTrans);
		//Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_ADD,req);
		logger.debug("------>>end updateUser<<------");
		return form;
	}
	
	@PostMapping(value= {"/get/all"})
	public Object getAllUser(@ModelAttribute("id")RequestParamForm form,HttpServletRequest req) {
		logger.debug("------>>start getAllUser<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("count", form.getCount());
		dataTrans.addInput("start", form.getStart());
		dataTrans.addInput("order", form.getOrder());
		dataTrans.addInput("form", form);
		dataTrans = this.userService.getAllUsers(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.USER_GET_LIST, req);
		logger.debug("------>>end getAllUser<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/shop/id/{id}"})
	public Object getUserByShop(@PathVariable("id")Long id,HttpServletRequest req) {
		logger.debug("------>>start getUserByShop<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shop_id", id);
		dataTrans = this.userService.getUserByShopId(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.USER_GET_BY,req);
		logger.debug("------>>end getUserByShop<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/shop/code/{code}"})
	public Object getUserByShop(@PathVariable("code")String code, HttpServletRequest req) {
		logger.debug("------>>start getUserByShop<<------");
		HashMap<String,Object> reqParam = new HashMap<>();
		reqParam.put("shop_code", code);
		
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shop_code", code);
		dataTrans.addInput("reqParam", reqParam);
		
		dataTrans = this.userService.getUserByShopCode(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.USER_GET_BY,req);
		logger.debug("------>>end getUserByShop<<------");
		return res;
	}
	
	@PostMapping(value= {"/get/person/id/{person_id}"})
	public Object getUserByPersonId(@PathVariable("person_id")Long person_id, HttpServletRequest req) {
		logger.debug("------>>start getUserByPersonId<<------");
		HashMap<String,Object> reqParam = new HashMap<>();
		reqParam.put("person_id", person_id);
		
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("person_id", person_id);
		dataTrans.addInput("reqParam", reqParam);
		
		dataTrans = this.userService.getUserByPersonId(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.USER_GET_BY,req);
		logger.debug("------>>start getUserByPersonId<<------");
		return res;
	}
}
