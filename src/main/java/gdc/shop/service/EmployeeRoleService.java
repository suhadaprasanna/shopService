/**
 * 
 */
package gdc.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.shop.common.form.EmployeeRoleForm;
import gdc.shop.common.form.validation.FormValidationUtil.FormKey;
import gdc.shop.datamanager.access.EmployeeRoleAccess;
import gdc.shop.datamanager.pojo.EmployeeRole;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Service
public class EmployeeRoleService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeRoleService.class);

	@Autowired
	private EmployeeRoleAccess employeeRoleAccess;
	
	public DataTransfer addRole(DataTransfer dataTrans) {
		try {
			EmployeeRoleForm form = (EmployeeRoleForm)dataTrans.getInput("employeeRoleForm");
			
			if(form ==null) {
				dataTrans.addOutput(Key.ERROR, "not found form data");
				return dataTrans;
			}
			
			if(!form.validate(FormKey.EMPLOYEEROLEFORM, dataTrans)) {
				logger.debug("------>> cannot validate <<------");
				return dataTrans;
			}
			
			EmployeeRole emploeeRole = this.setEmploeeRoleData(form, null);
			dataTrans.addInput(Key.POJO_EMPLOYEE_ROLE, emploeeRole);
			
			dataTrans = this.employeeRoleAccess.addRole(dataTrans);
			
		} catch (Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
		}
		return dataTrans;
	}
	
	private EmployeeRole setEmploeeRoleData(EmployeeRoleForm form,EmployeeRole employeeRole) {
		if(form.getId()>=0) {
			employeeRole = employeeRoleAccess.getById(form.getId());
		}
		
		if(employeeRole==null) {
			employeeRole = new EmployeeRole();
		}
		if(employeeRole.getId()<=0||employeeRole.getId()!= form.getId()) {
			employeeRole.setId(form.getId());
		}
		if(employeeRole.getRole_code()==null||employeeRole.getRole_code().equals("")|| !employeeRole.getRole_code().equals(form.getRole_code())) {
			employeeRole.setRole_code(form.getRole_code());
		}
		if(employeeRole.getRole_name()==null||employeeRole.getRole_name().equals("")|| !employeeRole.getRole_name().equals(form.getRole_name())) {
			employeeRole.setRole_name(form.getRole_name());
		}
		if(employeeRole.getStatus()==null||employeeRole.getStatus().equals("")|| !employeeRole.getStatus().equals(form.getStatus())) {
			employeeRole.setStatus(form.getStatus());
		}
		
		return employeeRole;
	}

	/**
	 * @param dataTrans
	 * @return
	 */
	public DataTransfer getAllRoles(DataTransfer dataTrans) {
		try {
			List<EmployeeRole> list = this.employeeRoleAccess.getAllRoles();
			if(list != null && list.size()>0) {
				dataTrans.setStatus(Status.SUCCESS);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
			dataTrans.addOutput("employee_role_list", list);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
		}
		return dataTrans;
	}
	
	public DataTransfer getRoleByCode(DataTransfer dataTrans) {
		try {
			String code = (String)dataTrans.getInput("code");
			EmployeeRole role = this.employeeRoleAccess.getByCode(code);
			if(role != null) {
				dataTrans.setStatus(Status.SUCCESS);
			}
			dataTrans.addOutput("employee_role", role);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
		}
		return dataTrans;
	}
}
