/**
 * 
 */
package gdc.shop.common.form.validation;

import gdc.shop.common.form.validation.FormValidation;

import org.springframework.stereotype.Component;

import gdc.shop.common.form.Form;
import gdc.shop.common.form.ShopEmployeeForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class ShopEmployeeFormValidation implements FormValidation{

	/* (non-Javadoc)
	 * @see gdc.shop.common.form.validation.FormValidation#validate(gdc.shop.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		ShopEmployeeForm form = (ShopEmployeeForm) _form;
		boolean status = true;
		if(form.getUsername() == null || form.getUsername().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "username required");
			status=false;
		}
		if(form.getPassword() == null || form.getPassword().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "password required");
			status=false;
		}
		
		return status;
	}

}
