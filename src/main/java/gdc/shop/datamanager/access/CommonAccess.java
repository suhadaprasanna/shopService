/**
 * 
 */
package gdc.shop.datamanager.access;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.dao.APIDetailsRepository;
import gdc.shop.datamanager.dao.LabelPackRepository;
import gdc.shop.datamanager.pojo.APIDetails;
import gdc.shop.datamanager.pojo.LabelPack;

/**
 * @author suhada
 *
 */
@Repository("commonAccess")
@Transactional
public class CommonAccess {

	private static final Logger logger = LoggerFactory.getLogger(CommonAccess.class);
	
	@Autowired
	private APIDetailsRepository aPIDetailsRepository;
	
	@Autowired
	private LabelPackRepository labelPackRepository;
	
	public List<APIDetails> getAPIDetails() {
		logger.debug("------>> start getAPIDetails<<------");
		return this.aPIDetailsRepository.findAll();
	}

	public List<LabelPack> getLabelPack(String type) {
		return null;
	}
	
	public List<LabelPack> getLabelPacks() {
		return this.labelPackRepository.findAll();
	}
}
