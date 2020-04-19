/**
 * 
 */
package gdc.shop.controllers.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author suhada
 *
 */
@Component
public class ResponseGeneratorFactoryUtil {

	public enum ResponseKey{
		SHOP_ADD,SHOP_GET,SHOP_GET_LIST,
		ITEM_ADD,
		USER_GET_BY, USER_GET_LIST,
		LABEL_PACK_LIST, EMPLOYEE_ROLE_ADD, EMPLOYEE_ROLE_LIST, 
		SHOP_EMPLOYEE_LIST
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseGeneratorFactoryUtil.class);
	
	public Class getResponseClass(ResponseKey key) {
		Class type = null;
		switch (key) {
		case SHOP_ADD:
			type = ResponseForShopAdd.class;
			break;
		case SHOP_GET:
			type = ResponseForGetShop.class;
			break;
		case SHOP_GET_LIST:
			type = ResponseForGetShopList.class;
			break;
		case LABEL_PACK_LIST:
			type = ResponseForLabelPackList.class;
			break;
		case EMPLOYEE_ROLE_ADD:
			type = ResponseForEmployeeRoleAdd.class;
			break;
		case EMPLOYEE_ROLE_LIST:
			type = ResponseForEmployeeRoleGetList.class;
			break;
		case USER_GET_LIST:
			type = ResponseForUserGetList.class;
			break;
		case USER_GET_BY:
			type = ResponseForUserGetBy.class;
			break;
		case SHOP_EMPLOYEE_LIST:
			type = ResponseForShopEmployeeGetList.class;
			break;
		default:
			type = null;
			break;
		}
		return type;
	}
}
