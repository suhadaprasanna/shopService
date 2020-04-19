
/**
 * 
 */
package gdc.shop.common.form.validation;


/**
 * @author suhada
 *
 */

public class FormValidationUtil {
	
	public enum FormKey {
		SHOPFORM,SHOPCONTACTFORM,SHOPEMAILFORM,
		SHOPEMPFORM,
		ITEMFORM,
		USERFORM,
		EMPLOYEEROLEFORM
	}
	
	public static Class getFormClass(FormKey key) {
		if(key == FormKey.SHOPFORM) {
			return ShopFormValidation.class;
		}else if(key == FormKey.SHOPCONTACTFORM) {
			return ShopContactFormValidation.class;
		}else if(key == FormKey.ITEMFORM) {
			return ItemFormValidation.class;
		}else if(key == FormKey.SHOPEMPFORM) {
			return ShopEmployeeFormValidation.class;
		}else if(key == FormKey.USERFORM) {
			return UserFormValidation.class;
		}else if(key == FormKey.EMPLOYEEROLEFORM) {
			return EmployeeRoleFormValidation.class;
		}else {
			return null;
		}
	}
}
