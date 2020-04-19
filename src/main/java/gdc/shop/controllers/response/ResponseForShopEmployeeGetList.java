/**
 * 
 */
package gdc.shop.controllers.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.shop.datamanager.pojo.EmployeeRole;
import gdc.shop.datamanager.pojo.ShopEmployee;
import gdc.shop.datamanager.pojo.User;
import gdc.shop.dto.PersonDTO;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForShopEmployeeGetList extends ResponseGeneratorImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseForShopEmployeeGetList.class);

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
			logger.error("------>> Error :",e);
		}
		logger.debug("------>>End generate<<------");
		return res;
	}

	/**
	 * @param dataTrans
	 * @param outputs
	 */
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		logger.debug("------>> start processOutputs<<------");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<ShopEmployee> list = (List)dataTrans.getOutput("shop_employee_list");
			ArrayList _list = new ArrayList();
			if(list != null && list.size()>0) {
				for (ShopEmployee shopEmployee : list) {
					HashMap emp = new HashMap<>();
					emp.put("id", shopEmployee.getId());
					try {
						if(shopEmployee.getEmployee_role() != null) {
							EmployeeRole role = shopEmployee.getEmployee_role();
							HashMap _role = new HashMap<>();
							_role.put("id", role.getId());
							_role.put("role_code", role.getRole_code());
							_role.put("role_name", role.getRole_name());
							emp.put("employee_role_id", role.getId());
							emp.put("employee_role", _role);
						}
					}catch(Exception e) {
						logger.error("------>> Error in setting role.",e);
					}
					emp.put("username", shopEmployee.getUsername());
					emp.put("password", shopEmployee.getPassword());
					emp.put("shop_id", shopEmployee.getShop().getId());
					emp.put("status", shopEmployee.getStatus());
					emp.put("user_id", shopEmployee.getUser().getId());
					emp.put("register_date", shopEmployee.getRegister_date());
					try {
						if(shopEmployee.getUser()!=null) {
							User user = shopEmployee.getUser();
							HashMap usr = new HashMap<>();
							try {
								if(user.getPersonDTO() != null) {
									HashMap prsn = new HashMap<>();
									PersonDTO personDTO = user.getPersonDTO();
									prsn.put("id", personDTO.getId());
									prsn.put("first_name", personDTO.getFirst_name());
									prsn.put("last_name", personDTO.getLast_name());
									prsn.put("middle_name", personDTO.getMiddle_name());
									prsn.put("sur_name", personDTO.getSur_name());
									prsn.put("nic", personDTO.getNic());
									prsn.put("gender", personDTO.getGender());
									prsn.put("status", personDTO.getStatus());
									prsn.put("birth_day", personDTO.getBirth_day() != null ? sdf.format(personDTO.getBirth_day()) : "-");
									usr.put("person", prsn);
								}
							}catch(Exception e) {
								logger.error("------>> Error in setting person.",e);
							}
							usr.put("id", user.getId());
							usr.put("person_id", user.getPerson());
							usr.put("status", user.getStatus());
							emp.put("user", usr);
						}
					}catch(Exception e) {
						logger.error("------>> Error in setting user.",e);
					}
					_list.add(emp);
				}
			}
			outputs.put("shop_employee_list", _list);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> start processOutputs<<------");
	}

	/**
	 * @param dataTrans
	 * @param inputs
	 */
	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		logger.debug("------>> start processInputs<<------");
		try {
			inputs.put("form", dataTrans.getInput("reqParam"));
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> end processInputs<<------");
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
