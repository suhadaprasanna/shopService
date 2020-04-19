/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.HashMap;
import java.util.List;

import gdc.shop.datamanager.pojo.Shop;

/**
 * @author suhada
 *
 */
public interface ShopCustomRepository extends CustomRepository<Shop> {

	public long getlastInsertedId();
	
	public HashMap<String, Object> findShopList(HashMap<String, Object> param);
	
}
