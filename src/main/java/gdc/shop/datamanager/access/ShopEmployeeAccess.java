/**
 * 
 */
package gdc.shop.datamanager.access;

import java.util.HashMap;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.dao.ShopEmployeeRepository;
import gdc.shop.datamanager.pojo.ShopEmployee;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Repository("shopEmployeeAccess")
@Transactional
public class ShopEmployeeAccess {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopEmployeeAccess.class);
	
	@Autowired
	private ShopEmployeeRepository shopEmployeeRepository;
	
	public DataTransfer save(DataTransfer dataTrans) {
		logger.debug("------>>start save<<------");
		try {
			ShopEmployee shopEmployee = (ShopEmployee)dataTrans.getInput(Key.POJO_SHOP_EMPLOYEE);
			this.shopEmployeeRepository.save(shopEmployee);
			if(shopEmployee.getId()>0) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_SHOP_EMPLOYEE, shopEmployee);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
		}catch(Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "error in saving shop employee");
		}
		logger.debug("------>>end save<<------");
		return dataTrans;
	}
	
	public ShopEmployee getByUsername(String username) {
		logger.debug("------>> start getByUsername <<------");
		logger.debug("------>> username: "+username);
		ShopEmployee user = null;
		try {
			user = this.shopEmployeeRepository.findByUsername(username);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> shop employee: "+user);
		logger.debug("------>> end getByUsername <<------");
		return user;
	}
	
	public ShopEmployee getByUsernameAndPassword(String username, String password) {
		logger.debug("------>> start getByUsernameAndPassword <<------");
		logger.debug("------>> username: "+username+", password: "+password);
		ShopEmployee user = null;
		try {
			user = this.shopEmployeeRepository.findByUsernameAndPassword(username,password);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> shop employee: "+user);
		logger.debug("------>> end getByUsernameAndPassword <<------");
		return user;
	}

	/**
	 * @param shop_id
	 * @return
	 */
	public List<ShopEmployee> getEmployiesByShopId(HashMap param) {
		logger.debug("------>> start getEmployiesByShopId <<------");
		List<ShopEmployee>list = null;
		try {
			list = this.shopEmployeeRepository.findByShopId(param);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> shop employee list: "+list);
		logger.debug("------>> end getEmployiesByShopId <<------");
		return list;
	}
}
