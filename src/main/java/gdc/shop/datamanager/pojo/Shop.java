/**
 * 
 */
package gdc.shop.datamanager.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author suhada
 *
 */
@Entity
@Table(name = "shop", catalog = "gdc_shop")
public class Shop {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@Column(name = "shop_name", nullable = false, length = 100)
	private String shop_name;
	@Column(name = "shop_code", nullable = false, length = 100)
	private String shop_code;
	@Column(name = "shop_register_number", nullable = true, length = 150)
	private String shop_register_number;
	@Temporal(TemporalType.DATE)
	@Column(name = "shop_register_date") 
	private Date shop_register_date;
	@Column(name = "addressl1", nullable = true, length = 100)
	private String addressl1;
	@Column(name = "addressl2", nullable = true, length = 100)
	private String addressl2;
	@Column(name = "addressl3", nullable = true, length = 100)
	private String addressl3;
	@Temporal(TemporalType.DATE)
	@Column(name = "sys_add_date")
	private Date sys_add_date;
	@Column(name = "status", length = 5)
	private String status;
	@Column(name = "province", nullable = true, length = 10)
	private String province;
	@Column(name = "district", nullable = true, length = 10)
	private String district;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", nullable = false)
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
	private Set<ShopContact> shopContacts = new HashSet<ShopContact>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
	private Set<ShopEmail> shopEmails = new HashSet<ShopEmail>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
	private Set<ShopEmployee> shopEmployees = new HashSet<ShopEmployee>();
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the shop_name
	 */
	public String getShop_name() {
		return shop_name;
	}
	/**
	 * @param shop_name the shop_name to set
	 */
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	/**
	 * @return the shop_code
	 */
	public String getShop_code() {
		return shop_code;
	}
	/**
	 * @param shop_code the shop_code to set
	 */
	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}
	/**
	 * @return the shop_register_number
	 */
	public String getShop_register_number() {
		return shop_register_number;
	}
	/**
	 * @param shop_register_number the shop_register_number to set
	 */
	public void setShop_register_number(String shop_register_number) {
		this.shop_register_number = shop_register_number;
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
	 * @return the addressl1
	 */
	public String getAddressl1() {
		return addressl1;
	}
	/**
	 * @param addressl1 the addressl1 to set
	 */
	public void setAddressl1(String addressl1) {
		this.addressl1 = addressl1;
	}
	/**
	 * @return the addressl2
	 */
	public String getAddressl2() {
		return addressl2;
	}
	/**
	 * @param addressl2 the addressl2 to set
	 */
	public void setAddressl2(String addressl2) {
		this.addressl2 = addressl2;
	}
	/**
	 * @return the addressl3
	 */
	public String getAddressl3() {
		return addressl3;
	}
	/**
	 * @param addressl3 the addressl3 to set
	 */
	public void setAddressl3(String addressl3) {
		this.addressl3 = addressl3;
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
	 * @return the status
	 */
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
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the shopContacts
	 */
	public Set<ShopContact> getShopContacts() {
		return shopContacts;
	}
	/**
	 * @param shopContacts the shopContacts to set
	 */
	public void setShopContacts(Set<ShopContact> shopContacts) {
		this.shopContacts = shopContacts;
	}
	/**
	 * @return the shopEmails
	 */
	public Set<ShopEmail> getShopEmails() {
		return shopEmails;
	}
	/**
	 * @param shopEmails the shopEmails to set
	 */
	public void setShopEmails(Set<ShopEmail> shopEmails) {
		this.shopEmails = shopEmails;
	}
	public Set<ShopEmployee> getShopEmployees() {
		return shopEmployees;
	}
	public void setShopEmployees(Set<ShopEmployee> shopEmployees) {
		this.shopEmployees = shopEmployees;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
