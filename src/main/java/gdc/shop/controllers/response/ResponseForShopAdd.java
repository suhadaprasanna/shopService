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
import gdc.shop.datamanager.pojo.Shop;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForShopAdd extends ResponseGeneratorImpl {

	private static final Logger logger = LoggerFactory.getLogger(ResponseForShopAdd.class);
	
	/* (non-Javadoc)
	 * @see gdc.shop.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer, java.util.HashMap)
	 */
	@Override
	public HashMap<String, Object> generate(DataTransfer dataTrans, HashMap<String, Object> res) {
		logger.debug("------>>Start generate<<------");
		try {
			// create inputs
			HashMap<String, Object> inputs = new HashMap<String, Object>();
			this.processInputs(dataTrans, inputs);
			
			// create outputs
			HashMap<String, Object> outputs = new HashMap<String, Object>();
			this.processOutputs(dataTrans, outputs);
			
			res.put(Key.INPUTS, inputs);
			res.put(Key.OUTPUTS, outputs);
		}catch (Exception e) {
			logger.error("Error while generating response "+e);
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
	
	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		inputs.put("form", dataTrans.getInput("reqParam"));
	}
	
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		logger.debug("------>> start processOutputs<<------");
		try {
			Shop shop = (Shop)dataTrans.getOutput(Key.POJO_SHOP);
			if(shop!=null) {
				HashMap _shop = new HashMap();
				_shop.put("id", shop.getId());
				_shop.put("shop_name", shop.getShop_name());
				_shop.put("shop_code", shop.getShop_code());
				_shop.put("shop_register_number", shop.getShop_register_number());
				_shop.put("shop_register_date", shop.getShop_register_date());
				_shop.put("shop_register_date", shop.getShop_register_date());
				_shop.put("addressl1", shop.getAddressl1());
				_shop.put("addressl2", shop.getAddressl2());
				_shop.put("addressl3", shop.getAddressl3());
				_shop.put("sys_add_date", shop.getSys_add_date());
				_shop.put("status", shop.getStatus());
				_shop.put("province", shop.getProvince());
				_shop.put("district", shop.getDistrict());
				outputs.put("form", _shop);
			}
		}catch(Exception e) {
			logger.error("------>> Error in finding Shop Object",e);
		}
		logger.debug("------>> end processOutputs<<------");
	}

	
}
