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
@Table(name = "label_pack", catalog = "gdc_shop")
public class LabelPack {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	@Column(name = "property_name", nullable = false, length = 100)
	private String property_name;
	@Column(name = "ENG", nullable = false, length = 100)
	private String ENG;
	@Column(name = "SIN", nullable = false, length = 100)
	private String SIN;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProperty_name() {
		return property_name;
	}
	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}
	public String getENG() {
		return ENG;
	}
	public void setENG(String eNG) {
		ENG = eNG;
	}
	public String getSIN() {
		return SIN;
	}
	public void setSIN(String sIN) {
		SIN = sIN;
	}
	
	
}
