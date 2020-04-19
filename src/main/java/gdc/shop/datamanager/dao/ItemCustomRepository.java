/**
 * 
 */
package gdc.shop.datamanager.dao;

import gdc.shop.datamanager.pojo.Item;

/**
 * @author suhada
 *
 */
public interface ItemCustomRepository extends CustomRepository<Item> {

	public long getlastInsertedId();
}
