/**
 * 
 */
package gdc.shop.common.form.validation;

import gdc.shop.common.form.validation.FormValidation;

import org.springframework.stereotype.Component;

import gdc.shop.common.form.Form;
import gdc.shop.common.form.ShopContactForm;
import gdc.shop.common.form.ShopForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class ShopContactFormValidation implements FormValidation{

	/* (non-Javadoc)
	 * @see gdc.shop.common.form.validation.FormValidation#validate(gdc.shop.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		ShopContactForm form = (ShopContactForm) _form;
		boolean status = true;
		if(form.getContact() == null || form.getContact().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "contact number required");
			status=false;
		}
		return status;
	}

}
