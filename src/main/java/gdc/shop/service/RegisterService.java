/**
 * 
 */
package gdc.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import gdc.shop.common.form.RegisterForm;
import gdc.shop.controllers.RegisterController;
import gdc.shop.datamanager.access.ShopAccess;
import gdc.shop.dto.PersonDTO;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Service
public class RegisterService {

	private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);
	
	@Autowired
	private WebClient.Builder webClientBuilder; 
	
	@Autowired
	private ShopAccess shopAccess;
	
	public DataTransfer register(DataTransfer dataTrans) {
		logger.debug("------>> start register<<------");
		try {
			RegisterForm form = (RegisterForm)dataTrans.getInput("registerForm");
			if(form==null) {
				logger.warn("------>> not found form");
				return dataTrans;
			}
			
			logger.debug("------>> form.getPerson_id:"+form.getPerson_id());
			if(form.getPerson_id()<=0) {
				//this.registerPersonDetails(form);
			}
			
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> end register<<------");
		return dataTrans;
	}
	
	private void registerPersonDetails(RegisterForm form) {
		try {
			 PersonDTO personDTO = this.webClientBuilder.build()
			.post()
			.uri("http://localhost:8181/person/add")
			.retrieve()
			.bodyToMono(PersonDTO.class)
			.block();
			 
			 logger.debug("------>> PersonDTO:"+personDTO);
			 
			 if(personDTO != null) {
				 form.setPerson_id(personDTO.getId());
			 }
			 
		}catch(Exception e) {
			logger.error("------>> Error Test :",e);
		}
	}

	
}
