/**
 * 
 */
package gdc.shop.common.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author suhada
 *
 */
public class ShopForm extends Form{
	
	private long id;
	private String shop_name;
	private String shop_code;
	private String shop_register_number;
	private String status;
	private String addressl1;
	private String addressl2;
	private String addressl3;
	private String contact1;
	private String contact2;
	private ShopContactForm[] contacts;
	private String email;
	private ShopEmailForm[] emails;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date shop_register_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sys_add_date;
	
	private long user_id;
	private long person_id;
	
	private String username;
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date person_register_date;
	private String employee_role_code;
	
	private int count;
	private int page;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_code() {
		return shop_code;
	}

	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}

	public String getShop_register_number() {
		return shop_register_number;
	}

	public void setShop_register_number(String shop_register_number) {
		this.shop_register_number = shop_register_number;
	}

	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the addressl
	 */
	public String getAddressl1() {
		return addressl1;
	}
	/**
	 * @param addressl the addressl to set
	 */
	public void setAddressl1(String addressl1) {
		this.addressl1 = addressl1;
	}
	/**
	 * @return the address2
	 */
	public String getAddressl2() {
		return addressl2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddressl2(String addressl2) {
		this.addressl2 = addressl2;
	}
	/**
	 * @return the address3
	 */
	public String getAddressl3() {
		return addressl3;
	}
	/**
	 * @param address3 the address3 to set
	 */
	public void setAddressl3(String addressl3) {
		this.addressl3 = addressl3;
	}
	public String getFullAddress() {
		return this.addressl1+", "+this.addressl2+", "+this.addressl3;
	}
	/**
	 * @return the shop_register_date
	 */
	public Date getShop_register_date() {
		return shop_register_date;
	}
	/**
	 * @param shop_register_date the shop_register_date to set
	 */
	public void setShop_register_date(Date shop_register_date) {
		this.shop_register_date = shop_register_date;
	}
	/**
	 * @return the sys_add_date
	 */
	public Date getSys_add_date() {
		return sys_add_date;
	}
	/**
	 * @param sys_add_date the sys_add_date to set
	 */
	public void setSys_add_date(Date sys_add_date) {
		this.sys_add_date = sys_add_date;
	}
	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getContact1() {
		return contact1;
	}
	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}
	public String getContact2() {
		return contact2;
	}
	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}
	public ShopContactForm[] getContacts() {
		return contacts;
	}
	public void setContacts(ShopContactForm[] contacts) {
		this.contacts = contacts;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ShopEmailForm[] getEmails() {
		return emails;
	}
	public void setEmails(ShopEmailForm[] emails) {
		this.emails = emails;
	}
	public long getPerson_id() {
		return person_id;
	}
	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPerson_register_date() {
		return person_register_date;
	}

	public void setPerson_register_date(Date person_register_date) {
		this.person_register_date = person_register_date;
	}

	public String getEmployee_role_code() {
		return employee_role_code;
	}

	public void setEmployee_role_code(String employee_role_code) {
		this.employee_role_code = employee_role_code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
