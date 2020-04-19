/**
 * 
 */
package gdc.utility.apiconnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import gdc.shop.datamanager.access.CommonAccess;
import gdc.shop.datamanager.pojo.APIDetails;
import gdc.utility.spring.ApplicationContextUtils;

/**
 * @author suhada
 *
 */
public abstract class APIConnector {

	private static final Logger logger = LoggerFactory.getLogger(APIConnector.class);
	
	HashMap<String, HashMap> apiMap = new HashMap<>();
	
	@Autowired
	protected WebClient.Builder webClientBuilder; 
	
	private CommonAccess commonAccess;
	
	@Autowired
	public final void setCommonAccess(CommonAccess commonAccess) {
		this.commonAccess = commonAccess;
	}

	protected void retriveAPIDetails() {
		logger.warn("------>> start retriveAPIDetails <<------");
		logger.debug("commonAccess: "+commonAccess);
		List<APIDetails> list = commonAccess.getAPIDetails();
		logger.warn("------>> "+list);
		if(list != null && list.size()>0) {
			ObjectMapper oMapper = new ObjectMapper();
			for (APIDetails apiDetails : list) {
				try {
					HashMap map = oMapper.convertValue(apiDetails, HashMap.class);
					apiMap.put(apiDetails.getApi_key(), map);
				}catch (Exception e) {
					logger.error("------>>Error ",e);
				}
			}
		}
		logger.warn("------>> end retriveAPIDetails <<------");
	}
	
	public HashMap getAPIDetails(String key) {
		if(apiMap == null || apiMap.size()<=0) {
			this.retriveAPIDetails();
		}
		return apiMap.get(key);
	}
	
	public HashMap getAllAPIDetails() {
		if(apiMap == null || apiMap.size()<=0) {
			this.retriveAPIDetails();
		}
		return apiMap;
	}
}
