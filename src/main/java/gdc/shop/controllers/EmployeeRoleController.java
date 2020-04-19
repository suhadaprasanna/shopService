/**
 * 
 */
package gdc.shop.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gdc.shop.common.form.EmployeeRoleForm;
import gdc.shop.controllers.response.ResponseGeneratorFactory;
import gdc.shop.controllers.response.ResponseGeneratorFactoryUtil.ResponseKey;
import gdc.shop.service.EmployeeRoleService;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value="/emprole")
public class EmployeeRoleController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeRoleController.class);
	
	@Autowired
	private ResponseGeneratorFactory resFactory;
	
	@Autowired
	private EmployeeRoleService employeeRoleService;
	
	@PostMapping(value="/add")
	public Object addRole(@RequestBody EmployeeRoleForm form, HttpServletRequest req) {
		logger.debug("------>>start addRole<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans.addInput("employeeRoleForm", form);
		dataTrans.addInput("reqParam", req.getParameterMap());
		dataTrans = this.employeeRoleService.addRole(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.EMPLOYEE_ROLE_ADD,req);
		logger.debug("------>>end addRole<<------");
		return res;
	}
	
	@PostMapping(value="/get")
	public Object getAll(HttpServletRequest req) {
		logger.debug("------>>start getAll<<------");
		DataTransfer dataTrans = new DataTransfer();
		dataTrans = this.employeeRoleService.getAllRoles(dataTrans);
		Object res = this.resFactory.getResponse(dataTrans, ResponseKey.EMPLOYEE_ROLE_LIST,req);
		logger.debug("------>>end getAll<<------");
		return res;
	}
}
