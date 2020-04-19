/**
 * 
 */
package gdc.shop.datamanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gdc.shop.datamanager.pojo.LabelPack;

/**
 * @author suhada
 *
 */
@Repository
public interface LabelPackRepository extends JpaRepository<LabelPack, Integer>,ShopCustomRepository{

	
}
