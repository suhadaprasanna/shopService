/**
 * 
 */
package gdc.shop.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gdc.shop.datamanager.access.ShopAccess;

/**
 * @author suhada
 *
 */
@Component
public class Util {

	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	
	@Autowired
	private ShopAccess shopAccess;
	
	public String getNextShopCode() {
		String code = "N/A";
		try {
			long id = this.shopAccess.getLastId();
			logger.debug("------>> id: "+id);
			code="SHP"+(id+1);
		}catch(Exception e) {
			logger.error("------>> Error while generating next shop code",e);
		}
		logger.warn("------>> shop code: "+code);
		return code;
	}
}
