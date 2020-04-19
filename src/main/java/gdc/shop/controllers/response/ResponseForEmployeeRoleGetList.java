/**
 * 
 */
package gdc.shop.controllers.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.shop.datamanager.pojo.EmployeeRole;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForEmployeeRoleGetList extends ResponseGeneratorImpl{

	private static final Logger logger = LoggerFactory.getLogger(ResponseForEmployeeRoleGetList.class);
	
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

	/**
	 * @param dataTrans
	 * @param outputs
	 */
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		try {
			List<EmployeeRole> list = (List<EmployeeRole>)dataTrans.getOutput("employee_role_list");
			if(list != null && list.size()>0) {
				ArrayList<HashMap> _list = new ArrayList();
				for (EmployeeRole empRole : list) {
					HashMap role = new HashMap<>();
					role.put("id", empRole.getId());
					role.put("role_code", empRole.getRole_code());
					role.put("role_name", empRole.getRole_name());
					role.put("status", empRole.getStatus());
					_list.add(role);
				}
				outputs.put("employee_role_list", _list);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		
	}

	/**
	 * @param dataTrans
	 * @param inputs
	 */
	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		try {
			inputs.put("form", dataTrans.getInput("reqParam"));
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		
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
