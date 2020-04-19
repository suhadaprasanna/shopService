/**
 * 
 */
package gdc.shop.datamanager.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.shop.datamanager.pojo.ShopEmployee;

/**
 * @author suhada
 *
 */
@Repository
public interface ShopEmployeeRepository extends JpaRepository<ShopEmployee, Long>,ShopEmployeeCustomRepository{

	@Query(value = "SELECT u FROM ShopEmployee u WHERE u.username = :username")
	ShopEmployee findByUsername(@Param("username") String username);

	@Query(value = "SELECT u FROM ShopEmployee u WHERE u.username = :username AND u.password=:password")
	ShopEmployee findByUsernameAndPassword(@Param("username") String username, @Param("password")String password);

//	@Query(value = "SELECT u FROM ShopEmployee u WHERE u.shop.id = :shop_id")
//	List<ShopEmployee> findByShopId(@Param("shop_id")long shop_id);
	
}
