/**
 * 
 */
package gdc.shop.controllers.response;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.shop.controllers.response.ResponseGeneratorImpl;
import gdc.shop.datamanager.pojo.Item;
import gdc.shop.datamanager.pojo.Shop;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForItemAdd extends ResponseGeneratorImpl {

	private static final Logger logger = LoggerFactory.getLogger(ResponseForItemAdd.class);
	
	/* (non-Javadoc)
	 * @see gdc.shop.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer, java.util.HashMap)
	 */
	@Override
	public HashMap<String, Object> generate(DataTransfer dataTrans, HashMap<String, Object> res) {
		logger.debug("------>>Start generate<<------");
		try {
			// create inputs
			HashMap<String, Object> inputs = new HashMap<String, Object>();
			inputs.put("form", dataTrans.getInput("reqParam"));
			
			// create outputs
			HashMap<String, Object> outputs = new HashMap<String, Object>();
			try {
				Item item = (Item)dataTrans.getOutput(Key.POJO_ITEM);
				HashMap _item = new HashMap();
				
				inputs.put("form", _item);
			}catch(Exception e) {
				logger.error("------>> Error ",e);
			}
			res.put(Key.INPUTS, inputs);
			res.put(Key.OUTPUTS, outputs);
		}catch (Exception e) {
			logger.error("------>> Error while generating response ",e);
		}
		logger.debug("------>>End generate<<------");
		return res;
	}

	/* (non-Javadoc)
	 * @see gdc.shop.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public Object generate(DataTransfer dataTrans) {
		// TODO Auto-generated method stub
		return null;
	}

}
