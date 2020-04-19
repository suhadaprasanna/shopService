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
import gdc.shop.datamanager.pojo.User;

/**
 * @author suhada
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT u FROM User u WHERE u.person = :id")
	User findByPersonId(@Param("id")long id);

	@Query(value = "SELECT u FROM User u JOIN FETCH u.shops s WHERE s.id = :shop_id")
	User findByShopId(@Param("shop_id")long shop_id);
	
	@Query(value = "SELECT u FROM User u JOIN FETCH u.shops s  WHERE s.shop_code = :shop_code")
	User findByShopCode(@Param("shop_code")String shop_code);
	
}
