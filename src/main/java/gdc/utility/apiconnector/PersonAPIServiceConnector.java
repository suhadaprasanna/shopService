/**
 * 
 */
package gdc.utility.apiconnector;

import java.util.Arrays;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.shop.dto.PersonDTO;

/**
 * @author suhada
 *
 */
@Component
public class PersonAPIServiceConnector extends APIConnector{

	private static final Logger logger = LoggerFactory.getLogger(PersonAPIServiceConnector.class);
	
	public static String KEY = "personService";
	
	private String url;
	private HashMap map;
	
	@PostConstruct
	private void  init() {
		this.map = this.getAPIDetails(PersonAPIServiceConnector.KEY);
		logger.debug(map.toString());
		if(this.map != null) {
			url = (String)this.map.get("api_url");
		}
	}
	
	public PersonDTO getPersonDetailsById(long id) {
		String url = this.url+"/person/get/id/"+id+"?ctype=int";
		logger.debug("------>> url: "+url);
		PersonDTO dt = null;
		try {
		dt = webClientBuilder.build()
				.post()
				.uri(url)
				.retrieve()
				.bodyToMono(PersonDTO.class)
				.block();
		}catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug(dt.toString());
		return dt;
	}
	
	public PersonDTO[] getPersonDetailsByIds(Long[] ids) {
		String idlist = "";
		for(int i=0; i<ids.length; i++){
			idlist+=(ids[i]+",");
		}
		String url = this.url+"/person/get/ids/"+idlist+"?ctype=int";
		logger.debug("------>> url: "+url);
		PersonDTO[] dt = null;
		try {
		dt = webClientBuilder.build()
				.post()
				.uri(url)
				.retrieve()
				.bodyToMono(PersonDTO[].class)
				.block();
		}catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug(dt.toString());
		return dt;
	}
	
}
