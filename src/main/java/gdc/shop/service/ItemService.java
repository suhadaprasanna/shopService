/**
 * 
 */
package gdc.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.shop.common.form.ItemForm;
import gdc.shop.common.form.validation.FormValidationUtil.FormKey;
import gdc.shop.controllers.ItemController;
import gdc.shop.datamanager.access.ItemAccess;
import gdc.shop.datamanager.pojo.Item;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Service
public class ItemService {

	private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemAccess itemAccess;
	
	public DataTransfer addItem(DataTransfer dataTrans) {
		logger.debug("------>> start addShop <<------");
		try {
			ItemForm form = (ItemForm)dataTrans.getInput("itemForm");
			if(form ==null) {
				dataTrans.addOutput(Key.ERROR, "not found form data");
				return dataTrans;
			}
			// start validation
			if(!form.validate(FormKey.ITEMFORM,dataTrans)) {
				logger.debug("------>> cannot validate <<------");
				return dataTrans;
			}
			Item item = new Item();
			this.setItemData(form, item);
			
			dataTrans.addInput(Key.POJO_ITEM, item);
			try {
				dataTrans = this.itemAccess.save(dataTrans);
			}catch(Exception e) {
				logger.error("------>> Error ",e);
			}
		}catch(Exception e) {
			logger.error("------>> Error",e);
		}
		return dataTrans;
	}

	private void setItemData(ItemForm form, Item item) {
		item.setId(form.getId());
		if(item.getItem_name()==null||item.getItem_name().equals("")|| !item.getItem_name().equals(form.getItem_name())) {
			item.setItem_name(form.getItem_name());
		}
		if(item.getItem_code()==null||item.getItem_code().equals("")|| !item.getItem_code().equals(form.getItem_code())) {
			item.setItem_code(form.getItem_code());
		}
		if(item.getItem_barcode()==null||item.getItem_barcode().equals("")|| !item.getItem_barcode().equals(form.getItem_barcode())) {
			item.setItem_barcode(form.getItem_barcode());
		}
		if(item.getStatus()==null||item.getStatus().equals("")|| !item.getStatus().equals(form.getStatus())) {
			item.setStatus(form.getStatus());
		}
	}

}
