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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import gdc.shop.dto.PersonDTO;

/**
 * @author suhada
 *
 */
@Entity
@Table(name = "user", catalog = "gdc_shop")
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@Column(name = "person", nullable = false)
	private long person;
	@Column(name = "status", nullable = false, length = 5)
	private String status;
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", length = 10)
	private Date created_date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<ShopEmployee> shopEmployees = new HashSet<ShopEmployee>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Shop> shops = new HashSet<Shop>();
	
	@Transient
	private PersonDTO personDTO;
	
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
	 * @return the person
	 */
	public long getPerson() {
		return person;
	}
	public void setPerson(long person) {
		this.person = person;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Set<ShopEmployee> getShopEmployees() {
		return shopEmployees;
	}
	public void setShopEmployees(Set<ShopEmployee> shopEmployees) {
		this.shopEmployees = shopEmployees;
	}
	public Set<Shop> getShops() {
		return shops;
	}
	public void setShops(Set<Shop> shops) {
		this.shops = shops;
	}
	public PersonDTO getPersonDTO() {
		return personDTO;
	}
	public void setPersonDTO(PersonDTO personDTO) {
		this.personDTO = personDTO;
	}
	
	
	
}
