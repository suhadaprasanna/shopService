/**
 * 
 */
package gdc.shop.common.form.validation;

import gdc.shop.common.form.validation.FormValidation;

import java.util.Date;

import org.springframework.stereotype.Component;

import gdc.shop.common.form.Form;
import gdc.shop.common.form.RegisterForm;
import gdc.shop.common.form.ShopForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class RegisterFormValidation implements FormValidation{

	/* (non-Javadoc)
	 * @see gdc.shop.common.form.validation.FormValidation#validate(gdc.shop.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		RegisterForm form = (RegisterForm) _form;
		boolean status = true;
		if(form.getShop_name() == null || form.getShop_name().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "shop name required");
			status=false;
		}
		if(form.getShop_name() != null && !form.getShop_name().equals("") && !this.validation.isName(form.getShop_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter valid shop name");
			status=false;
		}
		if(form.getShop_code() == null || form.getShop_code().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "shop code required");
			status=false;
		}
		
		if(form.getSur_name() != null && !form.getSur_name().equals("") && !this.validation.isString(form.getSur_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "sur name is not a valide name");
			status=false;
		}
		
		if(form.getFirst_name() == null || form.getFirst_name().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "first name required");
			status=false;
		}else if(form.getFirst_name() != null && !form.getFirst_name().equals("") && !this.validation.isString(form.getFirst_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "first name is not a valide name");
			status=false;
		}
		if(form.getMiddle_name() != null && !form.getMiddle_name().equals("") && !this.validation.isString(form.getMiddle_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "middle name is not a valide name");
			status=false;
		}else if(form.getLast_name() == null || form.getLast_name().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "last name required");
			status=false;
		}
		
		if(form.getLast_name() != null && !form.getLast_name().equals("") && !this.validation.isString(form.getLast_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "last name is not a valide name");
			status=false;
		}
		
		if(form.getPerson_email() != null && !form.getPerson_email().equals("") && !this.validation.isEmail(form.getPerson_email())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter valid person email");
			status=false;
		}
		if(form.getBirth_day() != null && form.getBirth_day().getTime() < new Date().getTime()) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter valid birth day");
			status=false;
		}
		if(form.getNic() != null && !form.getNic().equals("") && !this.validation.isNIC(form.getNic())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "not a valid nic");
			status=false;
		}
		
		return status;
	}

}
