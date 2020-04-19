/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.shop.datamanager.pojo.Shop;

/**
 * @author suhada
 *
 */
@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>,ShopCustomRepository{

	@Query(value = "SELECT s FROM Shop s WHERE s.shop_name = :name")
	Shop findByShopName(@Param("name") String name);
	
	@Query(value = "SELECT s FROM Shop s WHERE s.shop_name like %:name%")
	List<Shop> findByShopNameLike(@Param("name") String name);
	
	@Query(value = "SELECT s FROM Shop s WHERE s.shop_code = :code")
	Shop findByShopCode(@Param("code") String code);
	
	@Query(value = "SELECT s FROM Shop s WHERE s.shop_code like %:code%")
	List<Shop> findByShopCodeLike(@Param("code") String code);
	
	@Query(value = "SELECT count(s) FROM Shop s")
	Long getShopCount();
	
	@Query(value = "SELECT count(s) FROM Shop s WHERE s.status=:status")
	Long getShopCount(@Param("status") String status);

	@Query(value = "SELECT s FROM Shop s WHERE s.user.id=:user_id")
	List<Shop> findByUserId(long user_id);
}
