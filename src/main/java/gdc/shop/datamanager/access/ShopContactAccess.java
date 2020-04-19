/**
 * 
 */
package gdc.shop.datamanager.access;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.dao.ShopContactRepository;
import gdc.shop.datamanager.pojo.Shop;
import gdc.shop.datamanager.pojo.ShopContact;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Repository("shopContactAccess")
@Transactional
public class ShopContactAccess {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopContactAccess.class);
	
	@Autowired
	private ShopContactRepository shopContactRepository;
	
	public DataTransfer save(DataTransfer dataTrans) {
		logger.debug("------>>start save<<------");
		try {
			ShopContact shopContact = (ShopContact)dataTrans.getInput(Key.POJO_SHOP_CONTACT);
			this.shopContactRepository.save(shopContact);
			if(shopContact.getId()>0) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_SHOP_CONTACT, shopContact);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
		}catch(Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "error in saving shop contact");
		}
		logger.debug("------>>end save<<------");
		return dataTrans;
	}
	
	public ShopContact getByShopAndContact(long shopId, String contact) {
		logger.debug("------>>start getByShopAndContact<<------");
		ShopContact shopContact = null;
		try {
			logger.debug("------>> shopId:"+shopId+", contact:"+contact);
			if(shopId>0 && !contact.equals("")) {
				shopContact = this.shopContactRepository.getByShopAndContact(shopId, contact);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end getByShopAndContact<<------");
		return shopContact;
	}
}
