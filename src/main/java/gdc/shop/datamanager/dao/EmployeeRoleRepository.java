/**
 * 
 */
package gdc.shop.datamanager.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.shop.datamanager.pojo.EmployeeRole;

/**
 * @author suhada
 *
 */
@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer>{

	@Query(value = "SELECT e FROM EmployeeRole e WHERE e.role_code = :code")
	EmployeeRole findByCode(@Param("code") String code);

}
