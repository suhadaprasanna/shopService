/**
 * 
 */
package gdc.shop.common.form;

/**
 * @author suhada
 *
 */
public class ItemForm extends Form{

	private long id;
	private String item_name;
	private String item_code;
	private String item_barcode;
	private String status;
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
	 * @return the item_name
	 */
	public String getItem_name() {
		return item_name;
	}
	/**
	 * @param item_name the item_name to set
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	/**
	 * @return the item_code
	 */
	public String getItem_code() {
		return item_code;
	}
	/**
	 * @param item_code the item_code to set
	 */
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	/**
	 * @return the item_barcode
	 */
	public String getItem_barcode() {
		return item_barcode;
	}
	/**
	 * @param item_barcode the item_barcode to set
	 */
	public void setItem_barcode(String item_barcode) {
		this.item_barcode = item_barcode;
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
	
	
}
