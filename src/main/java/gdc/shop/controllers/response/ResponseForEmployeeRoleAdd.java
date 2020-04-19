/**
 * 
 */
package gdc.shop.controllers.response;

import java.util.HashMap;

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
public class ResponseForEmployeeRoleAdd extends ResponseGeneratorImpl{

	private static final Logger logger = LoggerFactory.getLogger(ResponseForEmployeeRoleAdd.class);
	
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

	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		try {
			inputs.put("form", dataTrans.getInput("reqParam"));
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
	}
	
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		try {
			EmployeeRole employeeRole = (EmployeeRole)dataTrans.getOutput(Key.POJO_EMPLOYEE_ROLE);
			if(employeeRole != null) {
				HashMap _employeeRole = new HashMap();
				_employeeRole.put("id", employeeRole.getId());
				_employeeRole.put("role_name", employeeRole.getRole_name());
				_employeeRole.put("role_code", employeeRole.getRole_code());
				_employeeRole.put("status", employeeRole.getStatus());
				outputs.put("employee_role", _employeeRole);
			}
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
