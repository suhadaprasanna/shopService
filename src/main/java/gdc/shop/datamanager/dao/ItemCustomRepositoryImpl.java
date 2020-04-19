/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.pojo.Shop;

/**
 * @author suhada
 *
 */

@Repository
@Transactional(readOnly = true)
public class ItemCustomRepositoryImpl implements ItemCustomRepository{

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ItemCustomRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public long getlastInsertedId() {
		try {
			List<Shop> list = this.entityManager.createQuery("SELECT i FROM Item i ORDER BY i.id DESC LIMIT 1",Shop.class)
			.setMaxResults(1)
			.getResultList();
			if(list.size()>0) {
				return list.get(0).getId();
			}
			logger.debug("------>> not found any items<<------");
		}catch(Exception e) {
			logger.error("------>> Error while getting last inserted id: ",e);
		}
		return 0;
	}
	
	
}
