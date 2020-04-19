/**
 * 
 */
package gdc.shop.common.form.validation;

import org.springframework.stereotype.Component;

import gdc.shop.common.form.Form;
import gdc.shop.common.form.ItemForm;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Component
public class ItemFormValidation implements FormValidation{

	/* (non-Javadoc)
	 * @see gdc.shop.common.form.validation.FormValidation#validate(gdc.shop.common.form.Form, gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public boolean validate(Form _form, DataTransfer dataTrans) {
		boolean status = true;
		ItemForm form = (ItemForm)_form;
		
		if(form.getItem_name() != null && !form.getItem_name().equals("") && !this.validation.isName(form.getItem_name())) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter valid item name");
			status=false;
		}
		if(form.getItem_code() != null && !form.getItem_code().equals("")) {
			dataTrans.setStatus(Status.WARNING);
			dataTrans.addOutput(Key.WARNING_LIST, "enter valid item code");
			status=false;
		}
		
		return status;
	}

}
