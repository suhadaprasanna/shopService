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
public class RegisterForm extends Form{
	
	private String shop_name;
	private String shop_code;
	private String shop_register_number;
	private String shop_addressl1;
	private String shop_addressl2;
	private String shop_addressl3;
	private String shop_addressl4;
	private String shop_email;
	private ShopEmailForm[] shop_emails;
	private String shop_contact1;
	private String shop_contact2;
	private ShopContactForm shop_contacts;
	private String shop_status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date shop_register_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date shop_sys_add_date;
	
	private long person_id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String sur_name;
	private String gender;
	private String nic;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth_day;
	private String person_email;
	private String person_addressl1;
	private String person_addressl2;
	private String person_addressl3;
	private String person_addressl4;
	private String person_contact1;
	private String person_contact2;
	private String person_status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date person_sys_add_date;
	
	private long user_id;
	
	private String username;
	private String password;
	
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
	public String getShop_addressl1() {
		return shop_addressl1;
	}
	public void setShop_addressl1(String shop_addressl1) {
		this.shop_addressl1 = shop_addressl1;
	}
	public String getShop_addressl2() {
		return shop_addressl2;
	}
	public void setShop_addressl2(String shop_addressl2) {
		this.shop_addressl2 = shop_addressl2;
	}
	public String getShop_addressl3() {
		return shop_addressl3;
	}
	public void setShop_addressl3(String shop_addressl3) {
		this.shop_addressl3 = shop_addressl3;
	}
	public String getShop_status() {
		return shop_status;
	}
	public void setShop_status(String shop_status) {
		this.shop_status = shop_status;
	}
	public Date getShop_register_date() {
		return shop_register_date;
	}
	public void setShop_register_date(Date shop_register_date) {
		this.shop_register_date = shop_register_date;
	}
	public Date getShop_sys_add_date() {
		return shop_sys_add_date;
	}
	public void setShop_sys_add_date(Date shop_sys_add_date) {
		this.shop_sys_add_date = shop_sys_add_date;
	}
	public long getPerson_id() {
		return person_id;
	}
	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getSur_name() {
		return sur_name;
	}
	public void setSur_name(String sur_name) {
		this.sur_name = sur_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public Date getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(Date birth_day) {
		this.birth_day = birth_day;
	}
	public String getPerson_email() {
		return person_email;
	}
	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}
	public String getPerson_addressl1() {
		return person_addressl1;
	}
	public void setPerson_addressl1(String person_addressl1) {
		this.person_addressl1 = person_addressl1;
	}
	public String getPerson_addressl2() {
		return person_addressl2;
	}
	public void setPerson_addressl2(String person_addressl2) {
		this.person_addressl2 = person_addressl2;
	}
	public String getPerson_addressl3() {
		return person_addressl3;
	}
	public void setPerson_addressl3(String person_addressl3) {
		this.person_addressl3 = person_addressl3;
	}
	public String getPerson_addressl4() {
		return person_addressl4;
	}
	public void setPerson_addressl4(String person_addressl4) {
		this.person_addressl4 = person_addressl4;
	}
	public String getPerson_contact1() {
		return person_contact1;
	}
	public void setPerson_contact1(String person_contact1) {
		this.person_contact1 = person_contact1;
	}
	public String getPerson_contact2() {
		return person_contact2;
	}
	public void setPerson_contact2(String person_contact2) {
		this.person_contact2 = person_contact2;
	}
	public String getPerson_status() {
		return person_status;
	}
	public void setPerson_status(String person_status) {
		this.person_status = person_status;
	}
	public Date getPerson_sys_add_date() {
		return person_sys_add_date;
	}
	public void setPerson_sys_add_date(Date person_sys_add_date) {
		this.person_sys_add_date = person_sys_add_date;
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
	public String getShop_email() {
		return shop_email;
	}
	public void setShop_email(String shop_email) {
		this.shop_email = shop_email;
	}
	public String getShop_addressl4() {
		return shop_addressl4;
	}
	public void setShop_addressl4(String shop_addressl4) {
		this.shop_addressl4 = shop_addressl4;
	}
	public String getShop_contact1() {
		return shop_contact1;
	}
	public void setShop_contact1(String shop_contact1) {
		this.shop_contact1 = shop_contact1;
	}
	public String getShop_contact2() {
		return shop_contact2;
	}
	public void setShop_contact2(String shop_contact2) {
		this.shop_contact2 = shop_contact2;
	}
	
}
