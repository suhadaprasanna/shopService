/**
 * 
 */
package gdc.shop.datamanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gdc.shop.datamanager.pojo.APIDetails;

/**
 * @author suhada
 *
 */
@Repository
public interface APIDetailsRepository extends JpaRepository<APIDetails, Integer>{

}
