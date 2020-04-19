/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.shop.datamanager.pojo.Item;

/**
 * @author suhada
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>,ItemCustomRepository{

	@Query(value = "SELECT i FROM Item i WHERE i.item_name = :name")
	Item findByItemName(@Param("name") String name);
	
	@Query(value = "SELECT i FROM Item i WHERE i.item_name like %:name%")
	List<Item> findByItemNameLike(@Param("name") String name);
	
	@Query(value = "SELECT i FROM Item i WHERE i.item_code = :code")
	Item findByItemCode(@Param("code") String code);
	
	@Query(value = "SELECT i FROM Item i WHERE i.item_code like %:code%")
	List<Item> findByItemCodeLike(@Param("code") String code);
}
