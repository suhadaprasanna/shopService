/**
 * 
 */
package gdc.shop.datamanager.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author suhada
 *
 */
@Entity
@Table(name = "item", catalog = "gdc_shop")
public class Item {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@Column(name = "item_name", nullable = false, length = 100)
	private String item_name;
	@Column(name = "item_code", nullable = false, length = 100)
	private String item_code;
	@Column(name = "item_barcode", nullable = false, length = 100)
	private String item_barcode;
	@Column(name = "status", nullable = false, length = 100)
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
