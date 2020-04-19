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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdc.shop.common.form.RegisterForm;
import gdc.shop.controllers.response.ResponseGeneratorFactory;
import gdc.shop.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.shop.service.RegisterService;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value="/register")
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	ResponseGeneratorFactory resFactory;
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping(value= {"","/"})
	public String shop() {
		return "register controller";
	}
	
	@PostMapping(value="/add")
	public Object add(@RequestBody RegisterForm form, HttpServletRequest req) {
		logger.debug("------>>start add<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("registerForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.registerService.register(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.ITEM_ADD,req);
		logger.debug("------>>end add<<------");
		return null;
	}
}
