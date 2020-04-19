/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
public class ShopCustomRepositoryImpl implements ShopCustomRepository{

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopCustomRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public long getlastInsertedId() {
		logger.debug("------>> start getlastInsertedId<<------");
		long id = 0;
		try {
			List<Shop> list = this.entityManager.createQuery("SELECT s FROM Shop s order by s.id desc",Shop.class)
					.setMaxResults(1)
					.getResultList();
			if(list.size()>0) {
				id = list.get(0).getId();
			}
			logger.debug("------>> not found any shops<<------");
		}catch(Exception e) {
			id = -1;
			logger.error("------>> Error while getting last inserted id: ",e);
		}
		logger.debug("------>> last id:"+id+"<<------");
		logger.debug("------>> end getlastInsertedId<<------");
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see gdc.shop.datamanager.dao.ShopCustomRepository#getShopList(java.util.HashMap) 
	 * list=result set ,total=full record count
	 */
	@Override
	public HashMap<String, Object> findShopList(HashMap<String, Object> param) {
		logger.debug("------>> start getShopList<<------");
		try {
			int page = -1, count = -1;
			String shop_name="",shop_code="",status="";
			
			if(param.get("page") != null) {
				page = (int)param.get("page");
			}
			if(param.get("count") != null) {
				count = (int)param.get("count");
			}
			if(param.get("shop_name") != null) {
				shop_name = (String)param.get("shop_name");
			}
			if(param.get("shop_code") != null) {
				shop_code = (String)param.get("shop_code");
			}
			if(param.get("status") != null) {
				status = (String)param.get("status");
			}
			
			logger.debug("------>> params count:"+count+", page:"+page+", shop_name:"+shop_name+", shop_code:"+shop_code+", status:"+status+"");
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Shop> shopSelectQuery = criteriaBuilder.createQuery(Shop.class);
			Root<Shop> root = shopSelectQuery.from(Shop.class);
			shopSelectQuery.select(root);
			
			List<Predicate> predicates = new ArrayList<>();
			if(shop_name != null && !shop_name.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("shop_name").as(String.class), "%"+shop_name+"%"));
			}
			if(shop_code != null && !shop_code.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("shop_code").as(String.class), "%"+shop_code+"%"));
			}
			if(status != null && !status.equals("") && !status.equalsIgnoreCase("ALL")) {
				predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), status));
			}
			
			shopSelectQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
			
			TypedQuery<Shop> final_uery = this.entityManager.createQuery(shopSelectQuery);
			if(count>-1 && page >-1) {
				final_uery.setFirstResult((page-1)*count);
			}
			if(count>-1) {
				final_uery.setMaxResults(count);
			}
			List<Shop> list = final_uery.getResultList();
			logger.debug("------>> list:"+list);
			param.put("list", list);
			
			CriteriaQuery<Long> shopCountQuery = criteriaBuilder.createQuery(Long.class);
			shopCountQuery.select(criteriaBuilder.count(shopCountQuery.from(Shop.class)));
			Long total_count = this.entityManager.createQuery(shopCountQuery).getSingleResult();
			logger.debug("------>> total_count:"+total_count);
			param.put("total_count", total_count);
			
			int page_count = 0;
			page_count = (int) (total_count/count);
			if(total_count%count>0) {
				page_count+=1;
			}
			logger.debug("------>> page_count:"+page_count);
			param.put("page_count", page_count);
			
		} catch (Exception e) {
			logger.error("------>> Error while getting shop list: ",e);
		}
		logger.debug("------>> end getShopList<<------");
		return param;
	}

}
