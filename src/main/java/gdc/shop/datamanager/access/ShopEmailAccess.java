/**
 * 
 */
package gdc.shop.datamanager.access;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.dao.ShopEmailRepository;
import gdc.shop.datamanager.pojo.ShopContact;
import gdc.shop.datamanager.pojo.ShopEmail;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Repository("ShopEmailAccess")
@Transactional
public class ShopEmailAccess {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopEmailAccess.class);
	
	@Autowired
	private ShopEmailRepository shopEmailRepository;
	
	public DataTransfer save(DataTransfer dataTrans) {
		logger.debug("------>>start save<<------");
		try {
			ShopEmail shopEmail = (ShopEmail)dataTrans.getInput(Key.POJO_SHOP_EMAIL);
			this.shopEmailRepository.save(shopEmail);
			if(shopEmail.getId()>0) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_SHOP_EMAIL, shopEmail);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
		}catch(Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "error in saving shop email");
		}
		logger.debug("------>>end save<<------");
		return dataTrans;
	}

	/**
	 * @param id
	 * @param email
	 * @return
	 */
	public ShopEmail getByShopAndEmail(long shopId, String email) {
		logger.debug("------>>start getByShopAndEmail<<------");
		ShopEmail shopEmail = null;
		try {
			logger.debug("------>> shopId:"+shopId+", contact:"+email);
			if(shopId>0 && !email.equals("")) {
				shopEmail = this.shopEmailRepository.getByShopAndEmail(shopId, email);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>end getByShopAndEmail<<------");
		return shopEmail;
	}
	
}
