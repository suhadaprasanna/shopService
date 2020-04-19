/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import gdc.shop.datamanager.pojo.Shop;
import gdc.shop.datamanager.pojo.ShopEmployee;

/**
 * @author suhada
 *
 */
public class ShopEmployeeCustomRepositoryImpl implements ShopEmployeeCustomRepository{

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopEmployeeCustomRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see gdc.shop.datamanager.dao.ShopEmployeeCustomRepository#findByShopId(java.util.HashMap)
	 */
	@Override
	public List<ShopEmployee> findByShopId(HashMap<String, Object> param) {
		logger.debug("------>> start findByShopId<<------");
		List<ShopEmployee> list = null;
		try {
			long shop_id = (long)param.get("shop_id");
			String stmt = "SELECT s FROM ShopEmployee s JOIN FETCH s.user WHERE s.shop.id="+shop_id;
			if(param.get("order") != null) {
				stmt+="ORDER BY s.id "+((String)param.get("order"));
			}
			Query query = this.entityManager.createQuery(stmt,ShopEmployee.class);
			if(param.get("start") != null) {
				query.setFirstResult((int)param.get("start"));
			}
			if(param.get("count") != null&&((int)param.get("count"))>0) {
				query.setMaxResults((int)param.get("count"));
			}
			list = query.getResultList();
		} catch (Exception e) {
			logger.error("------> Error ",e);
		}
		logger.debug("------>> shop employee list:"+list);
		logger.debug("------>> end findByShopId<<------");
		return list;
	}
	
	
}
