/**
 * 
 */
package gdc.shop.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gdc.shop.common.form.RequestParamForm;
import gdc.shop.common.form.ShopEmployeeForm;
import gdc.shop.controllers.response.ResponseGeneratorFactory;
import gdc.shop.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.shop.service.ShopEmployeeService;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value="/shopemp")
public class ShopEmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(ShopEmployeeController.class);
	
	@Autowired
	ResponseGeneratorFactory resFactory;
	
	@Autowired
	private ShopEmployeeService shopEmployeeService;
	
	@PostMapping(value="/add")
	public Object addEmployee(@ModelAttribute("form") ShopEmployeeForm form, HttpServletRequest req) {
		logger.debug("------>>start addEmployee<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shopEmployeeForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.shopEmployeeService.addNewEmployee(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_ADD,req);
		logger.debug("------>>end addEmployee<<------");
		return res;
	}
	
	@PostMapping(value="/update")
	public Object updateEmployee(@ModelAttribute("form") ShopEmployeeForm form, HttpServletRequest req) {
		logger.debug("------>>start updateEmployee<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shopEmployeeForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.shopEmployeeService.updateEmployee(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_ADD,req);
		logger.debug("------>>end updateEmployee<<------");
		return res;
	}
	
	@PostMapping(value="/get")
	public Object getEmployee(@ModelAttribute("form") RequestParamForm form,HttpServletRequest req) {
		logger.debug("------>>start getEmployee<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("count", form.getCount());
		dataTrans.addInput("start", form.getStart());
		dataTrans.addInput("order", form.getOrder());
		dataTrans.addInput("reqParam", form);
		dataTrans = this.shopEmployeeService.getShopEmployies(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_EMPLOYEE_LIST,req);
		logger.debug("------>>end getEmployee<<------");
		return res;
	}
	
	@PostMapping(value="/get/shopid/")
	public Object getEmployiesByShopId(@ModelAttribute("form") RequestParamForm form,
			HttpServletRequest req) {
		logger.debug("------>>start getEmployiesByShopId<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("shop_id", form.getShop_id());
		dataTrans.addInput("count", form.getCount());
		dataTrans.addInput("start", form.getStart());
		dataTrans.addInput("order", form.getOrder());
		dataTrans.addInput("reqParam", form);
		dataTrans = this.shopEmployeeService.getEmployiesByShopId(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_EMPLOYEE_LIST,req);
		logger.debug("------>>end getEmployiesByShopId<<------");
		return res;
	}
	
	@PostMapping(value="/get/unp/")
	public Object getByUsernameAndPassword(@ModelAttribute("form") RequestParamForm form,
			HttpServletRequest req) {
		logger.debug("------>>start getEmployiesByShopId<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("form", form);
		dataTrans = this.shopEmployeeService.getByUsernameAndPassword(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.SHOP_EMPLOYEE_LIST,req);
		logger.debug("------>>end getEmployiesByShopId<<------");
		return res;
	}
}
