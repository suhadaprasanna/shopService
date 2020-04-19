/**
 * 
 */
package gdc.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.shop.datamanager.access.CommonAccess;
import gdc.shop.datamanager.pojo.LabelPack;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Service
public class CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

	@Autowired
	private CommonAccess commonAccess;
	
	public DataTransfer getLabelPack(DataTransfer dataTrans) {
		try {
			String type = (String)dataTrans.getInput("type");
			if(type != null && !type.equals("")) {
				List<LabelPack> list = this.commonAccess.getLabelPacks();
				if(list != null && list.size()>0) {
					dataTrans.setStatus(Status.SUCCESS);
					dataTrans.addOutput("lang_pack_list", list);
				}else {
					dataTrans.setStatus(Status.WARNING);
					dataTrans.addOutput(Key.WARNING, "not found type");
				}
			}else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found type");
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wwrong");
		}
		return dataTrans;
	}
	
}
