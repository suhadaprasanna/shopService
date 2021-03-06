/**
 * 
 */
package gdc.shop.controllers.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.shop.controllers.response.ResponseGeneratorImpl;
import gdc.shop.datamanager.pojo.Shop;
import gdc.shop.datamanager.pojo.User;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForGetShopList extends ResponseGeneratorImpl {

	private static final Logger logger = LoggerFactory.getLogger(ResponseForGetShopList.class);
	
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

	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		inputs.put("form", dataTrans.getInput(Key.FORM));
	}
	
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		
		try {
			List<Shop> list = (List<Shop>)dataTrans.getOutput("shop_list");
			ArrayList shop_list = new ArrayList();
			for (Shop shop : list) {
				HashMap<String,Object> _shop = new HashMap();
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
				try {
					if(shop.getUser() != null) {
						User user = shop.getUser();
						HashMap<String,Object> _user = new HashMap();
						_user.put("id", user.getId());
						_user.put("person_id", user.getPerson());
						_user.put("status", user.getStatus());
						_shop.put("user", _user);
					}
				}catch(Exception e) {
					logger.error("------>> Error in setting user details ",e);
				}
				shop_list.add(_shop);
			}
			outputs.put("list", shop_list);
			
			if(dataTrans.getOutput("total_count")!=null) {
				Long total_count = (Long)dataTrans.getOutput("total_count");
				outputs.put("total_count", total_count);
			}
			if(dataTrans.getOutput("page_count")!=null) {
				int page_count = (int)dataTrans.getOutput("page_count");
				outputs.put("page_count", page_count);
			}
			
		}catch(Exception e) {
			logger.error("------>> Error in finding Shop Object",e);
		}
	}

	
}
