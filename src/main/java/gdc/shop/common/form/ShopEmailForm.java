/**
 * 
 */
package gdc.shop.common.form;

/**
 * @author suhada
 *
 */
public class ShopEmailForm extends Form{

	private long id;
	private String email;
	private String[] emails;
	private String status;
	private long shop_id;
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
	 * @return the emails
	 */
	public String[] getEmails() {
		return emails;
	}
	/**
	 * @param emails the emails to set
	 */
	public void setEmails(String[] emails) {
		this.emails = emails;
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
	 * @return the shop_id
	 */
	public long getShop_id() {
		return shop_id;
	}
	/**
	 * @param shop_id the shop_id to set
	 */
	public void setShop_id(long shop_id) {
		this.shop_id = shop_id;
	}
	
	
}
