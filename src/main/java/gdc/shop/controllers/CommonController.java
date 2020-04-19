/**
 * 
 */
package gdc.shop.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdc.shop.controllers.response.ResponseGeneratorFactory;
import gdc.shop.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.shop.service.CommonService;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value="/common")
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	ResponseGeneratorFactory resFactory;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/labelpack/{type}")
	public Object getLabelPack(@PathVariable("type") String type, HttpServletRequest req){
		logger.debug("------>>start getlangPack<<------");
		logger.debug("------>> type:"+type);
		HashMap<String,Object> reqParam = new HashMap<>();
		reqParam.put("type", type);
		
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("type", type);
		dataTrans.addInput("reqParam", reqParam);
		
		dataTrans = this.commonService.getLabelPack(dataTrans);
		
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.LABEL_PACK_LIST,req);
		logger.debug("------>>end getlangPack<<------");
		return res;
	}
}
