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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdc.shop.common.form.ItemForm;
import gdc.shop.controllers.response.ResponseGeneratorFactory;
import gdc.shop.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.shop.service.ItemService;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value="/item")
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	ResponseGeneratorFactory resFactory;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value= {"","/"})
	public String shop() {
		return "item controller";
	}
	
	@PostMapping(value="/add")
	public Object add(@ModelAttribute ItemForm form, HttpServletRequest req) {
		logger.debug("------>>start addShop<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("itemForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.itemService.addItem(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.ITEM_ADD,req);
		logger.debug("------>>end addShop<<------");
		return res;
	}
}
