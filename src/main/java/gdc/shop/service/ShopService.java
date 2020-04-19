/**
 * 
 */
package gdc.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdc.shop.common.form.validation.FormValidationUtil.FormKey;
import gdc.shop.datamanager.access.EmployeeRoleAccess;
import gdc.shop.datamanager.access.ShopAccess;
import gdc.shop.datamanager.access.ShopContactAccess;
import gdc.shop.datamanager.access.ShopEmailAccess;
import gdc.shop.datamanager.access.UserAccess;
import gdc.shop.datamanager.pojo.EmployeeRole;
import gdc.shop.datamanager.pojo.Shop;
import gdc.shop.datamanager.pojo.ShopContact;
import gdc.shop.datamanager.pojo.ShopEmail;
import gdc.shop.datamanager.pojo.ShopEmployee;
import gdc.shop.datamanager.pojo.User;
import gdc.shop.dto.PersonDTO;
import gdc.shop.common.Util;
import gdc.shop.common.form.ShopContactForm;
import gdc.shop.common.form.ShopEmailForm;
import gdc.shop.common.form.ShopForm;
import gdc.utility.apiconnector.PersonAPIServiceConnector;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Service
public class ShopService {

	private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

	@Autowired
	private Util util;

	@Autowired
	private ShopAccess shopAccess;

	@Autowired
	private ShopContactAccess shopContactAccess;

	@Autowired
	private ShopEmailAccess shopEmailAccess;
	
	@Autowired
	private UserAccess userAccess;
	
	@Autowired
	private EmployeeRoleAccess employeeRoleAccess;
	
	@Autowired
	private PersonAPIServiceConnector personAPIServiceCon;

	public DataTransfer addShop(DataTransfer dataTrans) {
		logger.debug("------>> start addShop <<------");
		try {
			ShopForm form = (ShopForm) dataTrans.getInput("shopForm");
			// check if form not null
			if (form == null) {
				logger.warn("------>> not found shop form");
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "not found form data");
				return dataTrans;
			}
			// start validation
			if (!form.validate(FormKey.SHOPFORM, dataTrans)) {
				logger.warn("------>> cannot validate <<------");
				return dataTrans;
			}
			
			// check if user id person id exist
			if(form.getPerson_id()<=0 && form.getUser_id()<=0) {
				logger.warn("------>> not found shop user");
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "not found user details");
				return dataTrans;
			}
			
			logger.debug("------>> user id: "+form.getUser_id()+", person id: "+form.getPerson_id());
			User user = null;
			if(form.getUser_id()>0) {
				user = this.userAccess.getById(form.getUser_id());
				logger.debug("------>> with user id: "+user);
			}
			if(user == null && form.getPerson_id()>0) {
				user = this.userAccess.getByPersonId(form.getPerson_id());
				logger.debug("------>> with person is from user: "+user);
				// if user still null will call person api and get person details and create user 
				if(user == null) {
					PersonDTO personDTO = this.personAPIServiceCon.getPersonDetailsById(form.getPerson_id());
					logger.debug("------>> person dto:"+personDTO);
					if(personDTO != null) {
						user = new User();
						user.setPerson(personDTO.getId());
						user.setStatus("Y");
						user.setCreated_date(new Date());
					}
				}
			}
			
			// check still user null. if user null will return
			if(user == null) {
				logger.warn("------>> not found user details");
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "not found user");
				return dataTrans;
			}
			
			Shop shop = this.setShopData(form, null);
			if(shop != null) {
				shop.setUser(user);
				user.getShops().add(shop);
			}
			
			ShopEmployee shopEmploye = this.setShopEmployeeData(form, null, shop, user);
			
			List<ShopContact> contacts = this.setShopContactData(form, shop);

			this.setShopEmailData(form, shop);

			boolean status = false;
			try {
				status = this.shopAccess.save(shop);
			} catch (Exception e) {
				logger.error("------>> Error", e);
				dataTrans.addOutput(Key.ERROR, "something went wrong");
			}
			if(status) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_SHOP, shop);
			}else {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "saving failed");
			}
			
		} catch (Exception e) {
			logger.error("------>> Error :", e);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		return dataTrans;
	}

	public DataTransfer addShopContact(DataTransfer dataTrans) {
		logger.debug("------>> start addShopContact <<------");
		ShopContactForm form = (ShopContactForm) dataTrans.getInput("shopContactForm");
		long shop_id = form.getShop_id();
		Shop shop = this.shopAccess.getShopById(shop_id);
		logger.debug("------>> shop:" + shop);
		if (shop != null) {
			ShopContact shop_contact = this.setShopContactData(form, shop);
			dataTrans.addInput(Key.POJO_SHOP_CONTACT, shop_contact);
			dataTrans = this.shopContactAccess.save(dataTrans);
		} else {
			dataTrans.addOutput(Key.ERROR, "not found shop");
		}
		logger.debug("------>> end addShopContact <<------");
		return dataTrans;
	}

	public DataTransfer addShopEmail(DataTransfer dataTrans) {
		logger.debug("------>> start addShopEmail <<------");
		ShopEmailForm form = (ShopEmailForm) dataTrans.getInput("shopEmailForm");
		long shop_id = form.getShop_id();
		Shop shop = this.shopAccess.getShopById(shop_id);
		logger.debug("------>> shop:" + shop);
		if (shop != null) {
			List<ShopEmail> list = this.setShopEmailData(form, shop);
			dataTrans.addInput(Key.POJO_SHOP_EMAIL, list.get(0));
			dataTrans = this.shopEmailAccess.save(dataTrans);
		} else {
			dataTrans.addOutput(Key.ERROR, "not found shop");
		}
		logger.debug("------>> end addShopEmail <<------");
		return dataTrans;
	}

	public DataTransfer getShopById(DataTransfer dataTrans) {
		logger.debug("------>> start getShopById <<------");
		long shop_id = (long) dataTrans.getInput(Key.SHOP_ID);
		if (shop_id > 0) {
			Shop shop = this.shopAccess.getShopById(shop_id);
			if (shop != null) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_SHOP, shop);
			}
		} else {
			dataTrans.setStatus(Status.FAIL);
			dataTrans.addOutput(Key.FAIL, "not found shop id");
		}
		logger.debug("------>> end getShopById <<------");
		return dataTrans;
	}

	public DataTransfer getLastAddedShopId(DataTransfer dataTrans) {
		logger.debug("------>> start getLastAddedShopId <<------");
		try {
			long id = this.shopAccess.getLastId();
			if (id > 0) {
				dataTrans.setStatus(Status.SUCCESS);
			} else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found last shop id");
			}
		} catch (Exception e) {
			logger.error("------>> Error :", e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> start getLastAddedShopId <<------");
		return dataTrans;
	}

	public DataTransfer getShopContactByid(DataTransfer dataTrans) {
		logger.debug("------>> start getShopContactByid <<------");
		try {

		} catch (Exception e) {
			logger.error("------>> Error ", e);
			dataTrans.setStatus(Status.ERROR);
		}
		logger.debug("------>> end getShopContactByid <<------");
		return dataTrans;
	}

	public DataTransfer getShops(DataTransfer dataTrans) {
		logger.debug("------>> start getShops <<------");
		try {
			ShopForm form = (ShopForm) dataTrans.getInput(Key.FORM);
			
			if (form == null) {
				logger.warn("------>> not found shop form");
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "not found form data");
				return dataTrans;
			}
			
			logger.debug("------>> count:"+form.getCount()+", page:"+form.getPage()+"<<------");

			HashMap<String, Object> param = new HashMap();
			param.put("count", form.getCount());
			param.put("page", form.getPage());
			param.put("status", form.getStatus());
			param.put("shop_code", form.getShop_code());
			param.put("shop_name", form.getShop_name());
			
			param = this.shopAccess.getShopList(param);
			
			List<Shop> list = (List<Shop>)param.get("list");
			Long total_count = (Long)param.get("total_count");
			int page_count = (int)param.get("page_count");
			
			if (list != null && list.size() > 0) {
				dataTrans.setStatus(Status.SUCCESS);
			} else {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.MESSAGE, "not found shops");
			}
			dataTrans.addOutput("shop_list", list);
			dataTrans.addOutput("total_count", total_count);
			dataTrans.addOutput("page_count", page_count);

		} catch (Exception e) {
			logger.error("------>> Error ", e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end getShops <<------");
		return dataTrans;
	}

	public DataTransfer getByUserId(DataTransfer dataTrans) {
		logger.debug("------>> start getByUserId <<------");
		try {
			long user_id = (long) dataTrans.getInput("user_id");
			if (user_id > 0) {
				List<Shop> list = this.shopAccess.getByUserId(user_id);
				if (list != null && list.size() > 0) {
					dataTrans.setStatus(Status.SUCCESS);
				} else {
					dataTrans.setStatus(Status.MESSAGE);
					dataTrans.addOutput(Key.MESSAGE, "not found shops");
				}
				dataTrans.addOutput("shop_list", list);
			} else {
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.WARNING, "not found user id");
			}
		} catch (Exception e) {
			logger.error("------>> Error ", e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		logger.debug("------>> end getByUserId <<------");
		return null;
	}
	
	public DataTransfer shopEnableDisable(DataTransfer dataTrans) {
		try {
			ShopForm form = (ShopForm)dataTrans.getInput(Key.FORM);
			if(form==null) {
				logger.warn("------>> not found shop form");
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "not found form data");
				return dataTrans;
			}
			if(form.getShop_code() == null || form.getShop_code().equals("") || form.getStatus()==null || form.getStatus().equals("")) {
				logger.warn("------>> not found shop code");
				dataTrans.setStatus(Status.WARNING);
				dataTrans.addOutput(Key.ERROR, "not found shop code or status");
				return dataTrans;
			}
			
			logger.debug("------>> code:"+form.getShop_code()+", status:"+form.getStatus());
			Shop shop = this.shopAccess.getByShopCode(form.getShop_code());
			if(form.getStatus().equalsIgnoreCase("Y")) {
				shop.setStatus("Y");
			}else {
				shop.setStatus("N");
			}
			
			boolean status = false;
			try {
				status = this.shopAccess.save(shop);
			}catch(Exception e) {
				status = false;
				logger.error("------>> Error (save failed): ",e);
				dataTrans.setStatus(Status.ERROR);
				dataTrans.addOutput(Key.ERROR, "action failed");
			}
			
			if(status) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_SHOP, shop);
			}else {
				dataTrans.setStatus(Status.FAIL);
				dataTrans.addOutput(Key.FAIL, "action failed");
			}
			
		} catch (Exception e) {
			logger.error("------>> Error : ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "something went wrong");
		}
		return dataTrans;
	}

	private Shop setShopData(ShopForm form, Shop shop) {
		logger.debug("------>> start setShopData<<------");

		if (shop == null && form.getId() > 0) {
			shop = this.shopAccess.getShopById(form.getId());
		}
		if (shop == null) {
			shop = new Shop();
		}

		if (shop.getId() == 0 && form.getId() > 0)
			shop.setId(form.getId());
		if (shop.getShop_code() == null || shop.getShop_code().equals("")
				|| !(shop.getShop_code().equals(form.getShop_code()))) {
			if (shop.getShop_code() == null || shop.getShop_code().equals("")) {
				logger.warn("------>> creating new shop code<<------");
				shop.setShop_code(this.util.getNextShopCode());
			} else {
				logger.warn("was shop code:" + shop.getShop_code() + ", fom shop code:" + form.getShop_code());
				shop.setShop_code(form.getShop_code());
			}
		}
		if ((shop.getShop_register_date() == null && form.getShop_register_date() != null)
				|| (shop.getShop_register_date() != null && form.getShop_register_date() != null
						&& (shop.getShop_register_date().getTime() != form.getShop_register_date().getTime()))) {
			logger.debug("shop.getShop_register_date()==null : " + (shop.getShop_register_date() == null));
			shop.setShop_register_date(form.getShop_register_date());

		}
		if (shop.getShop_name() == null || shop.getShop_name().equals("")
				|| !shop.getShop_name().equals(form.getShop_name())) {
			shop.setShop_name(form.getShop_name());
		}
		if (shop.getAddressl1() == null || shop.getAddressl1().equals("")
				|| !shop.getAddressl1().equals(form.getAddressl1())) {
			shop.setAddressl1(form.getAddressl1());
		}
		if (shop.getAddressl2() == null || shop.getAddressl2().equals("")
				|| !shop.getAddressl2().equals(form.getAddressl2())) {
			shop.setAddressl2(form.getAddressl2());
		}
		if (shop.getAddressl3() == null || shop.getAddressl3().equals("")
				|| !shop.getAddressl3().equals(form.getAddressl3())) {
			shop.setAddressl3(form.getAddressl3());
		}
		if (shop.getShop_register_number() == null || shop.getShop_register_number().equals("")) {
			shop.setShop_register_number(form.getShop_register_number());
		}
		if (shop.getSys_add_date() == null) {
			shop.setSys_add_date(new Date());
		}
		if (shop.getStatus() == null || shop.getStatus().equals("") || !shop.getStatus().equals(form.getStatus())) {
			shop.setStatus(form.getStatus());
		}
		logger.debug("------>> end setShopData<<------");
		return shop;
	}

	private List<ShopContact> setShopContactData(ShopForm form, Shop shop) {
		List<ShopContact> list = new ArrayList<>();
		logger.debug("------>> contact1:" + form.getContact1());
		if (form.getContact1() != null && !form.getContact1().equals("")) {
			ShopContact shopContact = null;
			if (shop != null) {
				shopContact = this.shopContactAccess.getByShopAndContact(shop.getId(), form.getContact1());
			}
			if (shopContact == null) {
				shopContact = new ShopContact();
			}
			if (shopContact.getContact() == null || shopContact.getContact().equals("")
					|| !shopContact.getContact().equals(form.getContact1()))
				shopContact.setContact(form.getContact1());
			if (shopContact.getStatus() == null || shopContact.getStatus().equals("")
					|| !shopContact.getStatus().equalsIgnoreCase(form.getStatus()))
				shopContact.setStatus(form.getStatus());
			if (shop != null) {
				shopContact.setShop(shop);
				shop.getShopContacts().add(shopContact);
			}
			list.add(shopContact);
		}

		logger.debug("------>> contact2:" + form.getContact2());
		if (form.getContact2() != null && !form.getContact2().equals("")) {
			ShopContact shopContact = null;
			if (shop != null) {
				shopContact = this.shopContactAccess.getByShopAndContact(shop.getId(), form.getContact2());
			}
			if (shopContact == null) {
				shopContact = new ShopContact();
			}
			if (shopContact.getContact() == null || shopContact.getContact().equals("")
					|| !shopContact.getContact().equals(form.getContact2()))
				shopContact.setContact(form.getContact2());
			if (shopContact.getStatus() == null || shopContact.getStatus().equals("")
					|| !shopContact.getStatus().equalsIgnoreCase(form.getStatus()))
				shopContact.setStatus(form.getStatus());
			if (shop != null) {
				shopContact.setShop(shop);
				shop.getShopContacts().add(shopContact);
			}
			list.add(shopContact);
		}
		return list;
	}

	private ShopContact setShopContactData(ShopContactForm form, Shop shop) {
		logger.debug("------>> contact1:" + form.getContact());
		ShopContact shopContact = null;
		if (form.getContact() != null && !form.getContact().equals("")) {
			if (shop != null) {
				shopContact = this.shopContactAccess.getByShopAndContact(shop.getId(), form.getContact());
			}
			if (shopContact == null) {
				shopContact = new ShopContact();
			}
			if (shopContact.getContact() == null || shopContact.getContact().equals("")
					|| !shopContact.getContact().equals(form.getContact()))
				shopContact.setContact(form.getContact());
			if (shopContact.getStatus() == null || shopContact.getStatus().equals("")
					|| !shopContact.getStatus().equalsIgnoreCase(form.getStatus()))
				shopContact.setStatus(form.getStatus());
			if (shop != null) {
				shopContact.setShop(shop);
				shop.getShopContacts().add(shopContact);
			}
		}
		return shopContact;
	}

	private List<ShopEmail> setShopEmailData(ShopForm form, Shop shop) {
		logger.debug("------>> email:" + form.getEmail());
		List<ShopEmail> list = new ArrayList<>();
		if (form.getEmail() != null && !form.getEmail().equals("")) {
			ShopEmail shopEmail = null;
			if (shopEmail == null && form.getId()>0) {
				shopEmail = this.shopEmailAccess.getByShopAndEmail(form.getId(), form.getEmail());
			}
			if (shopEmail == null) {
				shopEmail = new ShopEmail();
			}
			if (shopEmail.getEmail() == null || shopEmail.getEmail().equals("")
					|| !shopEmail.getEmail().equals(form.getEmail()))
				shopEmail.setEmail(form.getEmail());
			if (shopEmail.getStatus() == null || shopEmail.getStatus().equals("")
					|| !shopEmail.getStatus().equalsIgnoreCase(form.getStatus()))
				shopEmail.setStatus(form.getStatus());
			if (shop != null) {
				shopEmail.setShop(shop);
				shop.getShopEmails().add(shopEmail);
			}
			list.add(shopEmail);
		}

		logger.debug("------>> emails:" + form.getEmails());
		if (form.getEmails() != null && form.getEmails().length > 0) {
			for (ShopEmailForm shopEmailForm : form.getEmails()) {
				ShopEmail shopEmail = null;
				if (shopEmail == null && form.getId()>0) {
					shopEmail = this.shopEmailAccess.getByShopAndEmail(form.getId(), form.getEmail());
				}
				if (shopEmail == null) {
					shopEmail = new ShopEmail();
				}
				if (shopEmail.getEmail() == null || shopEmail.getEmail().equals("")
						|| !shopEmail.getEmail().equals(form.getEmail()))
					shopEmail.setEmail(form.getEmail());
				if (shopEmail.getStatus() == null || shopEmail.getStatus().equals("")
						|| !shopEmail.getStatus().equalsIgnoreCase(form.getStatus()))
					shopEmail.setStatus(form.getStatus());
				if (shop != null) {
					shopEmail.setShop(shop);
					shop.getShopEmails().add(shopEmail);
				}
				list.add(shopEmail);
			}
		}
		return list;
	}

	private List<ShopEmail> setShopEmailData(ShopEmailForm form, Shop shop) {
		logger.debug("------>> email:" + form.getEmail());
		List<ShopEmail> list = new ArrayList<>();
		if (form.getEmail() != null && !form.getEmail().equals("")) {
			ShopEmail shopEmail = null;
			if (shopEmail == null) {
				shopEmail = this.shopEmailAccess.getByShopAndEmail(form.getId(), form.getEmail());
			}
			if (shopEmail == null) {
				shopEmail = new ShopEmail();
			}
			if (shopEmail.getEmail() == null || shopEmail.getEmail().equals("")
					|| !shopEmail.getEmail().equals(form.getEmail()))
				shopEmail.setEmail(form.getEmail());
			if (shopEmail.getStatus() == null || shopEmail.getStatus().equals("")
					|| !shopEmail.getStatus().equalsIgnoreCase(form.getStatus()))
				shopEmail.setStatus(form.getStatus());
			if (shop != null) {
				shopEmail.setShop(shop);
				shop.getShopEmails().add(shopEmail);
			}
			list.add(shopEmail);
		}
		return list;
	}

	private ShopEmployee setShopEmployeeData(ShopForm form,ShopEmployee shopEmploye,Shop shop,User user) {
		if(shopEmploye==null) {
			shopEmploye=new ShopEmployee();
		}
		if(shopEmploye.getUsername()==null||shopEmploye.getUsername().equals("")|| !shopEmploye.getUsername().equals(form.getUsername())) {			
			shopEmploye.setUsername(form.getUsername());
		}
		if(shopEmploye.getUsername()==null||shopEmploye.getUsername().equals("")|| !shopEmploye.getUsername().equals(form.getUsername())) {
			shopEmploye.setPassword(form.getPassword());
		}
		if(shopEmploye.getSys_add_date()==null)
			shopEmploye.setSys_add_date(new Date());
		if(shopEmploye.getStatus()==null||shopEmploye.getStatus().equals("")|| !shopEmploye.getStatus().equals(form.getStatus()))
			shopEmploye.setStatus("Y");
		if(shopEmploye.getRegister_date()==null&&form.getPerson_register_date()!=null)
			shopEmploye.setRegister_date(form.getPerson_register_date());
		if(shopEmploye.getEmployee_role() == null) {
			EmployeeRole role = this.employeeRoleAccess.getByCode("ownr");
			shopEmploye.setEmployee_role(role);
		}else {
			if(form.getEmployee_role_code() != null && !form.getEmployee_role_code().equals("")) {
				EmployeeRole role = shopEmploye.getEmployee_role();
				if(!role.getRole_code().equals(form.getEmployee_role_code())) {
					role = this.employeeRoleAccess.getByCode(form.getEmployee_role_code());
					shopEmploye.setEmployee_role(role);
				}
			}
		}
		if(shop != null) {
			shopEmploye.setShop(shop);
			shop.getShopEmployees().add(shopEmploye);
		}
		if(user != null) {			
			shopEmploye.setUser(user);
			user.getShopEmployees().add(shopEmploye);
		}
		return shopEmploye;
	}

}
