/**
 * 
 */
package gdc.shop.common.form.validation;

import org.springframework.stereotype.Component;

import gdc.shop.common.form.EmployeeRoleForm;
import gdc.shop.common.form.Form;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class EmployeeRoleFormValidation implements FormValidation{

	/* (non-Javadoc)
	 * @see gdc.shop.common.form.validation.FormValidation#validate(gdc.shop.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		EmployeeRoleForm form = (EmployeeRoleForm)_form;
		boolean status = true;
		
		if(form.getRole_name()==null|| form.getRole_name().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "role name required");
			status = false;
		}
		if(form.getRole_code()==null|| form.getRole_code().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "role code required");
			status = false;
		}
		
		return status;
	}

}
