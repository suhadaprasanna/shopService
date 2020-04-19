/**
 * 
 */
package gdc.shop.datamanager.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author suhada
 *
 */
@Entity
@Table(name = "shop_emails", catalog = "gdc_shop")
public class ShopEmail {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	@Column(name = "status", nullable = false, length = 5)
	private String status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop", nullable = false)
	private Shop shop;
	
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the shop
	 */
	public Shop getShop() {
		return shop;
	}
	/**
	 * @param shop the shop to set
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
}
