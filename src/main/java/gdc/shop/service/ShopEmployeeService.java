/**
 * 
 */
package gdc.shop.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.shop.common.form.RequestParamForm;
import gdc.shop.common.form.ShopEmployeeForm;
import gdc.shop.common.form.validation.FormValidationUtil.FormKey;
import gdc.shop.datamanager.access.EmployeeRoleAccess;
import gdc.shop.datamanager.access.ShopAccess;
import gdc.shop.datamanager.access.ShopEmployeeAccess;
import gdc.shop.datamanager.access.UserAccess;
import gdc.shop.datamanager.pojo.EmployeeRole;
import gdc.shop.datamanager.pojo.Shop;
import gdc.shop.datamanager.pojo.ShopEmployee;
import gdc.shop.datamanager.pojo.User;
import gdc.shop.dto.PersonDTO;
import gdc.utility.apiconnector.PersonAPIServiceConnector;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Service
public class ShopEmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(ShopEmployeeService.class);

	@Autowired
	private UserAccess userAccess;

	@Autowired
	private ShopAccess shopAccess;

	@Autowired
	private EmployeeRoleAccess employeeRoleAccess;

	@Autowired
	private ShopEmployeeAccess shopEmployeeAccess;
	
	@Autowired
	private PersonAPIServiceConnector personAPIService;

	public DataTransfer addNewEmployee(DataTransfer dataTrans) {
		logger.debug("------>> start addNewEmployee <<------");
		try {
			ShopEmployeeForm form = (ShopEmployeeForm) dataTrans.getInput("shopEmployeeForm");
			if (form == null) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found form data");
				return dataTrans;
			}

			if (!form.validate(FormKey.SHOPFORM, dataTrans)) {
				logger.debug("------>> cannot validate <<------");
				return dataTrans;
			}

			ShopEmployee shopEmployee = new ShopEmployee();
			shopEmployee.setSys_add_date(new Date());
			this.setEmployeeData(form, shopEmployee);

			// set user details
			logger.debug("------>> user id:" + form.getUser_id());
			if (form.getUser_id() <= 0) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found user id");
				return dataTrans;
			} else {
				User user = this.userAccess.getById(form.getUser_id());
				if (user == null) {
					dataTrans.setStatus(Status.WARNING);
					dataTrans.addOutput(Key.WARNING, "not found user");
					logger.warn("------>> not found user details for " + form.getUser_id());
					return dataTrans;
				} else {
					shopEmployee.setUser(user);
				}
			}

			// set shop details
			logger.debug("------>> shop id:" + form.getShop_id());
			if (form.getShop_id() <= 0) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found shop id");
				return dataTrans;
			} else {
				Shop shop = this.shopAccess.getShopById(form.getShop_id());
				if (shop == null) {
					dataTrans.setStatus(Status.WARNING);
					dataTrans.addOutput(Key.WARNING, "not found shop");
					logger.warn("------>> not found shop details for " + form.getShop_id());
					return dataTrans;
				} else {
					shopEmployee.setShop(shop);
				}
			}

			// set role details
			logger.debug("------>> role id:" + form.getEmployee_role_id());
			if (form.getEmployee_role_id() <= 0) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found role id");
				return dataTrans;
			} else {
				EmployeeRole role = this.employeeRoleAccess.getById(form.getEmployee_role_id());
				if (role == null) {
					dataTrans.setStatus(Status.WARNING);
					dataTrans.addOutput(Key.WARNING, "not found employee role");
					logger.warn("------>> not found role details for " + form.getEmployee_role_id());
					return dataTrans;
				} else {
					shopEmployee.setEmployee_role(role);
				}
			}

			try {
				dataTrans.addInput(Key.POJO_SHOP_EMPLOYEE, shopEmployee);
				dataTrans = this.shopEmployeeAccess.save(dataTrans);
			} catch (Exception e) {
				dataTrans.setStatus(Status.ERROR);
				logger.error("------>> Error ", e);
			}

		} catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
			logger.error("------>> Error ", e);
		}
		logger.debug("------>> end addNewEmployee <<------");
		return dataTrans;
	}

	public DataTransfer updateEmployee(DataTransfer dataTrans) {
		logger.debug("------>> start updateEmployee <<------");
		try {

		} catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
			logger.error("------>> Error ", e);
		}
		logger.debug("------>> end updateEmployee <<------");
		return dataTrans;
	}

	public DataTransfer getShopEmployies(DataTransfer dataTrans) {
		logger.debug("------>> start getShopEmployies <<------");
		try {
			int count = (int) dataTrans.getInput("count");
			int start = (int) dataTrans.getInput("start");
			String order = (String) dataTrans.getInput("order");
			logger.debug("------>> count:"+count + ", start:" + start + ", order:" + order + "<<------");

			HashMap<String, Object> param = new HashMap();
			param.put("count", count);
			param.put("start", start);
			param.put("order", order);
		} catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
			logger.error("------>> Error ", e);
		}
		logger.debug("------>> end getShopEmployies <<------");
		return dataTrans;
	}

	public DataTransfer getEmployiesByShopId(DataTransfer dataTrans) {
		logger.debug("------>> start getEmployiesByShopId <<------");
		try {
			int count = (int) dataTrans.getInput("count");
			int start = (int) dataTrans.getInput("start");
			String order = (String) dataTrans.getInput("order");
			long shop_id = (long)dataTrans.getInput("shop_id");
			RequestParamForm form = (RequestParamForm) dataTrans.getInput("reqParam");
			logger.debug("------>> count:"+count + ", start:" + start + ", order:" + order + ", shop_id:"+shop_id+"<<------");

			HashMap<String, Object> param = new HashMap();
			param.put("count", count);
			param.put("start", start);
			param.put("order", order);
			param.put("shop_id", shop_id);
			List<ShopEmployee> list = this.shopEmployeeAccess.getEmployiesByShopId(param);
			if (list != null && list.size() > 0) {
				logger.debug("------>> with person details:"+form.isWith_person_details());
				if(form.isWith_person_details()) {
					this.fillWithPersonDetails(list);
				}
				dataTrans.setStatus(Status.SUCCESS);
			} else {
				dataTrans.setStatus(Status.MESSAGE);
				dataTrans.addOutput(Key.MESSAGE, "not found shop employees");
			}
			dataTrans.addOutput("shop_employee_list", list);
		} catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
			logger.error("------>> Error ", e);
		}
		logger.debug("------>> end getEmployiesByShopId <<------");
		return dataTrans;
	}
	
	public DataTransfer getByUsernameAndPassword(DataTransfer dataTrans) {
		logger.debug("------>> start getByUsernameAndPassword <<------");
		try {
			RequestParamForm form = (RequestParamForm) dataTrans.getInput("form");
			if(form!=null) {
				String username = form.getUsername();
				String password = form.getPassword();
				ShopEmployee employee = this.shopEmployeeAccess.getByUsernameAndPassword(username, password);
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_SHOP_EMPLOYEE, employee);
			}else {
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "not found form");
			}
		} catch (Exception e) {
			dataTrans.setStatus(Status.ERROR);
			logger.error("------>> Error ", e);
		}
		logger.debug("------>> end getByUsernameAndPassword <<------");
		return dataTrans;
	}

	private void setEmployeeData(ShopEmployeeForm form, ShopEmployee shopEmployee) {
		logger.debug("------>> start setEmployeeData <<------");
		shopEmployee.setId(form.getId());
		if (shopEmployee.getUsername() == null || shopEmployee.getUsername().equals("")
				|| !shopEmployee.getUsername().equals(form.getUsername())) {
			shopEmployee.setUsername(form.getUsername());
		}
		if (shopEmployee.getPassword() == null || shopEmployee.getPassword().equals("")
				|| !shopEmployee.getPassword().equals(form.getPassword())) {
			shopEmployee.setPassword(form.getPassword());
		}
		if (shopEmployee.getStatus() == null || shopEmployee.getStatus().equals("")
				|| !shopEmployee.getStatus().equals(form.getStatus())) {
			shopEmployee.setStatus(form.getStatus());
		}
		logger.debug("------>> end setEmployeeData <<------");
	}
	
	private void fillWithPersonDetails(List<ShopEmployee> list) {
		logger.debug("------>> start fillWithPersonDetails <<------");
		Stream<ShopEmployee> stream_shopEmployee = list.stream();
		//Long[] id_list = stream_shopEmployee.map(emp->emp.getId()).toArray(Long[]::new); 
		Long[] id_list = new Long[list.size()];
		for(int i=0; i<list.size(); i++) {
			logger.debug(i+"-"+list.get(i).getUser().getPerson());
			id_list[i] = list.get(i).getUser().getPerson();
		}
		logger.debug("------>> id_list:"+id_list.toString());
		PersonDTO[] person_list = this.personAPIService.getPersonDetailsByIds(id_list);
		logger.debug("------>> person_list:"+person_list+", size:"+(person_list != null ?person_list.length:"N/A"));
		if(person_list != null && person_list.length>0) {
			Stream<PersonDTO> stream_personDTO = Arrays.asList(person_list).stream();
			stream_shopEmployee.forEach(emp ->{
				try {
					PersonDTO pdto = stream_personDTO.filter(p->p.getId()==emp.getUser().getPerson()).toArray(PersonDTO[]::new)[0];
					emp.getUser().setPersonDTO(pdto);
				}catch(Exception e) {
					logger.error("------>> Error in adding person details: "+emp.getId(),e);
				}
			});
		}
		logger.debug("------>> end fillWithPersonDetails <<------");
	}
}
