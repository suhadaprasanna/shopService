/**
 * 
 */
package gdc.shop.datamanager.access;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.dao.UserRepository;
import gdc.shop.datamanager.pojo.User;

/**
 * @author suhada
 *
 */
@Repository("userAccess")
@Transactional
public class UserAccess {

	private static final Logger logger = LoggerFactory.getLogger(UserAccess.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public User getById(long id) {
		logger.debug("------>> start getById <<------");
		logger.debug("------>> id: "+id);
		User user = null;
		try {
			Optional<User> op = this.userRepository.findById(id);
			if(op.isPresent()) {
				user = op.get();
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> user: "+user);
		logger.debug("------>> end getById <<------");
		return user;
	}
	
	public User getByPersonId(long id) {
		logger.debug("------>> start getByPersonId <<------");
		User user = null;
		try {
			user = this.userRepository.findByPersonId(id);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> user: "+user);
		logger.debug("------>> end getByPersonId <<------");
		return user;
	}

	/**
	 * @param shop_id
	 * @return
	 */
	public User getByShopId(long shop_id) {
		logger.debug("------>> start getByShopId <<------");
		User user = null;
		try {
			user = this.userRepository.findByShopId(shop_id);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> end getByShopId <<------");
		return user;
	}
	
	public User getByShopCode(String shop_code) {
		logger.debug("------>> start getByShopCode <<------");
		User user = null;
		try {
			user = this.userRepository.findByShopCode(shop_code);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> end getByShopCode <<------");
		return user;
	}

	/**
	 * @param param
	 */
	public List<User> getAllUsers(HashMap<String, Object> param) {
		logger.debug("------>> start getAllUsers <<------");
		List<User> list = null;
		try {
			list = this.userRepository.findAll();
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> end getAllUsers <<------");
		return list;
	}
	
}
