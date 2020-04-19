/**
 * 
 */
package gdc.shop.datamanager.access;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.dao.ItemRepository;
import gdc.shop.datamanager.pojo.Item;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Repository("itemAccess")
@Transactional
public class ItemAccess {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ItemAccess.class);
	
	@Autowired
	private ItemRepository itemRepository;
	
	public DataTransfer save(DataTransfer dataTrans) {
		logger.debug("------>>start save<<------");
		try {
			Item shop = (Item)dataTrans.getInput(Key.POJO_ITEM);
			this.itemRepository.save(shop);
			if(shop.getId()>0) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_ITEM, shop);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
		}catch(Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "error in saving item");
		}
		logger.debug("------>>end save<<------");
		return dataTrans;
	}
	
	public long getLastId() {
		logger.debug("------>>start getLastId<<------");
		try {
			return this.itemRepository.getlastInsertedId();
		}catch(Exception e) {
			logger.error("------>> Error while getting last id",e);
			return 0;
		}finally {
			logger.debug("------>>end getLastId<<------");
		}
	}

	public Item getShopById(long id) {
		logger.debug("------>>start getShopById<<------");
		Item item = null;
		try {
			if(id>0) {
				Optional<Item> result = this.itemRepository.findById(id);
				logger.debug("------>> is present: "+result.isPresent());
				if(result.isPresent()) {
					item = result.get();
				}
			}else {
				logger.warn("------>> not found shop id");
			}
		}catch(Exception e) {
			logger.error("------>> Error while getting shop",e);
		}
		logger.debug("------>>end getShopById<<------");
		return item;
	}

}
