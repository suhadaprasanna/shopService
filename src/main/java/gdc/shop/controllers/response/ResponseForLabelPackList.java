/**
 * 
 */
package gdc.shop.controllers.response;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.shop.datamanager.pojo.LabelPack;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForLabelPackList extends ResponseGeneratorImpl{

	private static final Logger logger = LoggerFactory.getLogger(ResponseForLabelPackList.class);
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
		logger.debug("------>>Start processInputs<<------");
		try {
			inputs.put("form", dataTrans.getInput("reqParam"));
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>End processInputs<<------");
	}
	
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		logger.debug("------>>Start processOutputs<<------");
		try {
			List<LabelPack> list = (List)dataTrans.getOutput("lang_pack_list");
			String type = (String)dataTrans.getInput("type");
			logger.debug("------>> type:"+type);
			if(list != null && list.size()>0) {
				Field field = LabelPack.class.getDeclaredField(type);
				field.setAccessible(true);
				HashMap<String,String> _list = new HashMap<>();
				for (LabelPack labelPack : list) {
					String value = (String) field.get(labelPack);
					_list.put(labelPack.getProperty_name(), value);
				}
				outputs.put("label_pack_list", _list);
			}
		} catch (NoSuchFieldException | SecurityException e) {
			logger.error("------>> Error ",e);
		} catch (IllegalArgumentException e) {
			logger.error("------>> Error ",e);
		} catch (IllegalAccessException e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end processOutputs<<------");
	}
}
