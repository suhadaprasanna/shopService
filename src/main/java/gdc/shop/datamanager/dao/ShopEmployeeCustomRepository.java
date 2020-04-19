/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.HashMap;
import java.util.List;

import gdc.shop.datamanager.pojo.ShopEmployee;

/**
 * @author suhada
 *
 */
public interface ShopEmployeeCustomRepository extends CustomRepository<ShopEmployee>{

	public List<ShopEmployee> findByShopId(HashMap<String, Object> param);
}
