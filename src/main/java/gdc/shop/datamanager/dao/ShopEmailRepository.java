/**
 * 
 */
package gdc.shop.datamanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gdc.shop.datamanager.pojo.ShopEmail;

/**
 * @author suhada
 *
 */
@Repository
public interface ShopEmailRepository extends JpaRepository<ShopEmail, Long>{

	@Query(value = "SELECT se FROM ShopEmail se WHERE se.email = :email AND se.shop.id = :shopId")
	ShopEmail getByShopAndEmail(long shopId, String email);

}
