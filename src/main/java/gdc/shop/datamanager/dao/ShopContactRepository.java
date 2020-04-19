/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.shop.datamanager.pojo.ShopContact;

/**
 * @author suhada
 *
 */
@Repository
public interface ShopContactRepository extends JpaRepository<ShopContact, Long>{

	@Query(value = "SELECT sc FROM ShopContact sc WHERE sc.contact = :contact AND sc.shop.id = :shopId")
	public ShopContact getByShopAndContact(@Param("shopId")long shopId,@Param("contact")String contact);
	
	@Query(value = "SELECT sc FROM ShopContact sc WHERE sc.shop.id = :shopId")
	public List<ShopContact> getByShop(@Param("shopId")long shopId);
	
	@Query(value = "SELECT sc FROM ShopContact sc WHERE sc.shop.id = :shopId AND sc.status = :status")
	public List<ShopContact> getByShopWithStatus(@Param("shopId")long shopId, @Param("status") String status);
}
