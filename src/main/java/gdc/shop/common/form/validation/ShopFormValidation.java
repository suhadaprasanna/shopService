/**
 * 
 */
package gdc.shop.common.form.validation;

import gdc.shop.common.form.validation.FormValidation;

import org.springframework.stereotype.Component;

import gdc.shop.common.form.Form;
import gdc.shop.common.form.ShopForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class ShopFormValidation implements FormValidation{

	/* (non-Javadoc)
	 * @see gdc.shop.common.form.validation.FormValidation#validate(gdc.shop.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		ShopForm form = (ShopForm) _form;
		boolean status = true;
		if(form.getShop_name() == null || form.getShop_name().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "shop name required");
			status=false;
		}
		if(form.getShop_name() != null && !form.getShop_name().equals("") && !this.validation.isName(form.getShop_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING, "enter valid shop name");
			status=false;
		}
		if(form.getId()>0) {
			if(form.getShop_code() == null || form.getShop_code().equals("")) {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "shop code required");
				status=false;
			}
		}
		
		return status;
	}

}
