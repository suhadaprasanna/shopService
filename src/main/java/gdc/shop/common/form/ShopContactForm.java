/**
 * 
 */
package gdc.shop.common.form;

/**
 * @author suhada
 *
 */
public class ShopContactForm extends Form{

	private long id;
	private String contact;
	private String[] contacts;
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
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
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
	/**
	 * @return the contacts
	 */
	public String[] getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(String[] contacts) {
		this.contacts = contacts;
	}
	
	
}
