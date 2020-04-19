/**
 * 
 */
package gdc.shop.common.form.validation;

import org.springframework.stereotype.Component;

import gdc.shop.common.form.Form;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.validations.Validation;

/**
 * @author suhada
 *
 */
@Component
public interface FormValidation {

	public boolean validate (Form form,DataTransfer dataTrans);
	
	public Validation validation = new Validation(); 
	
}
