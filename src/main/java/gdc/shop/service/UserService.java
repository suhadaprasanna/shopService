/**
 * 
 */
package gdc.shop.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.shop.common.form.RequestParamForm;
import gdc.shop.controllers.UserController;
import gdc.shop.datamanager.access.UserAccess;
import gdc.shop.datamanager.pojo.ShopEmployee;
import gdc.shop.datamanager.pojo.User;
import gdc.shop.dto.PersonDTO;
import gdc.utility.apiconnector.PersonAPIServiceConnector;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserAccess userAccess;
	
	@Autowired
	private PersonAPIServiceConnector personAPIService;

	public DataTransfer getUserByShopId(DataTransfer dataTrans) {
		logger.debug("------>>start getUserByShopId <<------");
		try {
			long shop_id = (long) dataTrans.getInput("shop_id");
			if (shop_id > 0) {
				User user = this.userAccess.getByShopId(shop_id);
				if (user != null) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput("user", user);
				} else {
					dataTrans.setStatus(Status.MESSAGE);
					dataTrans.addOutput(Key.MESSAGE, "not found user");
				}
			} else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found shop id");
			}
		} catch (Exception e) {
			logger.error("------>> Error", e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>end getUserByShopId <<------");
		return dataTrans;
	}

	public DataTransfer getUserByShopCode(DataTransfer dataTrans) {
		logger.debug("------>>start getUserByShopId <<------");
		try {
			String shop_code = (String) dataTrans.getInput("shop_code");
			if (shop_code != null && !shop_code.equals("")) {
				User user = this.userAccess.getByShopCode(shop_code);
				if (user != null) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput("user", user);
				} else {
					dataTrans.setStatus(Status.MESSAGE);
					dataTrans.addOutput(Key.MESSAGE, "not found user");
				}
			} else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found shop code");
			}
		} catch (Exception e) {
			logger.error("------>> Error", e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>end getUserByShopId <<------");
		return dataTrans;
	}

	/**
	 * @param dataTrans
	 * @return
	 */
	public DataTransfer getUserByPersonId(DataTransfer dataTrans) {
		logger.debug("------>>start getUserByPersonId <<------");
		try {
			long person_id = (long) dataTrans.getInput("person_id");
			if (person_id >= 0) {
				User user = this.userAccess.getByPersonId(person_id);
				if (user != null) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput("user", user);
				} else {
					dataTrans.setStatus(Status.MESSAGE);
					dataTrans.addOutput(Key.MESSAGE, "not found user");
				}
			} else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found person id");
			}
		} catch (Exception e) {
			logger.error("------>> Error", e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>end getUserByPersonId <<------");
		return null;
	}

	/**
	 * @param dataTrans
	 * @return
	 */
	public DataTransfer getAllUsers(DataTransfer dataTrans) {
		logger.debug("------>>start getAllUsers <<------");
		try {
			int count = (int) dataTrans.getInput("count");
			int start = (int) dataTrans.getInput("start");
			String order = (String) dataTrans.getInput("order");
			RequestParamForm form = (RequestParamForm) dataTrans.getInput("form");
			logger.debug("------>> count:" + ", start:" + start + ", order:" + order + "<<------");
			
			HashMap<String, Object> param = new HashMap();
			param.put("count", count);
			param.put("start", start);
			param.put("order", order);
			
			List<User> list = this.userAccess.getAllUsers(param);
			if(list != null) {
				dataTrans.setStatus(Status.SUCCESS);
				if(list.size()<=0) {
					dataTrans.addOutput(Key.MESSAGE, "not found users");
				}else {
					if(form.isWith_person_details()) {
						this.fillWithPersonDetails(list);
					}
				}
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
			dataTrans.addOutput(Key.USER_LIST, list);
			
		} catch (Exception e) {
			logger.error("------>> Error", e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>>end getAllUsers <<------");
		return dataTrans;
	}
	
	private void fillWithPersonDetails(List<User> list) {
		logger.debug("------>> start fillWithPersonDetails <<------");
		Stream<User> stream_shopEmployee = list.stream();
		//Long[] id_list = stream_shopEmployee.map(emp->emp.getId()).toArray(Long[]::new); 
		Long[] id_list = new Long[list.size()];
		for(int i=0; i<list.size(); i++) {
			logger.debug(i+"-"+list.get(i).getPerson());
			id_list[i] = list.get(i).getPerson();
		}
		logger.debug("------>> id_list:"+id_list.toString());
		PersonDTO[] person_list = this.personAPIService.getPersonDetailsByIds(id_list);
		logger.debug("------>> person_list:"+person_list+", size:"+(person_list != null ?person_list.length:"N/A"));
		if(person_list != null && person_list.length>0) {
			stream_shopEmployee.forEach(emp ->{
				try {
					PersonDTO pdto = Arrays.asList(person_list).stream().filter(p->p.getId()==emp.getPerson()).toArray(PersonDTO[]::new)[0];
					emp.setPersonDTO(pdto);
				}catch(Exception e) {
					logger.error("------>> Error in adding person details: ",e);
				}
			});
		}
		logger.debug("------>> end fillWithPersonDetails <<------");
	}
}
