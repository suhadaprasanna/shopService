/**
 * 
 */
package gdc.shop.datamanager.access;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.dao.ShopRepository;
import gdc.shop.datamanager.pojo.Shop;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;
import net.bytebuddy.implementation.bytecode.Throw;

/**
 * @author suhada
 *
 */
@Repository("shopAccess")
@Transactional
public class ShopAccess {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopAccess.class);
	
	@Autowired
	private ShopRepository shopRepository;
	
	public boolean save(Shop shop)throws Exception {
		logger.debug("------>>start save<<------");
		boolean status = false;
		this.shopRepository.save(shop);
		if(shop.getId()>0) {
			status = true;
		}else {
			status = false;
		}
		logger.debug("------>>end save<<------");
		return status;
	}
	
	public long getLastId() {
		logger.debug("------>>start getLastId<<------");
		try {
			return this.shopRepository.getlastInsertedId();
		}catch(Exception e) {
			logger.error("------>> Error while getting last id",e);
			return 0;
		}finally {
			logger.debug("------>>end getLastId<<------");
		}
	}

	public Shop getShopById(long id) {
		logger.debug("------>>start getShopById<<------");
		Shop shop = null;
		try {
			if(id>0) {
				HashMap<String, Object> param = new HashMap();
				param.put("shop_id", id);
				Optional<Shop> result = this.shopRepository.findById(id);
				if(result.isPresent())
					shop = result.get();
			}else {
				logger.warn("------>> not found shop id");
			}
		}catch(Exception e) {
			logger.error("------>> Error while getting shop",e);
		}
		logger.debug("------>>shop: "+shop);
		logger.debug("------>>end getShopById<<------");
		return shop;
	}

	/**
	 * @param dataTrans
	 * @return
	 */
	public HashMap<String, Object> getShopList(HashMap<String, Object> param) {
		logger.debug("------>>start getShopList<<------");
		try {
			param = this.shopRepository.findShopList(param);
		}catch (Exception e) {
			logger.error("------>> Error",e);
		}
		logger.debug("------>>end getShopList<<------");
		return param;
	}

	public long getShopCount() {
		logger.debug("------>>start getShopCount<<------");
		long count =0;
		try {
			count = this.shopRepository.getShopCount();
			logger.debug("------>> count:"+count);
		} catch (Exception e) {
			logger.error("------>> Error",e);
		}
		logger.debug("------>>end getShopCount<<------");
		return count;
	}

	/**
	 * @param user_id
	 * @return
	 */
	public List<Shop> getByUserId(long user_id) {
		logger.debug("------>>start getByUserId<<------");
		List<Shop> list = null;
		try {
			list = this.shopRepository.findByUserId(user_id);
			logger.debug("------>> list.size():"+list.size());
		} catch (Exception e) {
			logger.error("------>> Error",e);
		}
		logger.debug("------>>end getByUserId<<------");
		return list;
	}

	/**
	 * @param shop_code
	 * @return
	 */
	public Shop getByShopCode(String shop_code) {
		logger.debug("------>>start getByShopCode<<------");
		Shop shop = this.shopRepository.findByShopCode(shop_code);
		logger.debug("------>>end getByShopCode<<------");
		return shop;
	}
	
	public List<Shop> getByShopCodeLike(String shop_code) {
		logger.debug("------>>start getByShopCode<<------");
		List<Shop> list = this.shopRepository.findByShopCodeLike(shop_code);
		logger.debug("------>>end getByShopCode<<------");
		return list;
	}
}
