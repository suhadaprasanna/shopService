/**
 * 
 */
package gdc.shop.datamanager.pojo;

import static javax.persistence.GenerationType.IDENTITY;

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

/**
 * @author suhada
 *
 */
@Entity
@Table(name = "employee_role", catalog = "gdc_shop")
public class EmployeeRole {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "role_name", nullable = false, length = 100)
	private String role_name;
	@Column(name = "role_code", nullable = false, length = 100)
	private String role_code;
	@Column(name = "status", nullable = false, length = 100)
	private String status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee_role", cascade = CascadeType.ALL)
	private Set<ShopEmployee> shopEmployees = new HashSet<ShopEmployee>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<ShopEmployee> getShopEmployees() {
		return shopEmployees;
	}
	public void setShopEmployees(Set<ShopEmployee> shopEmployees) {
		this.shopEmployees = shopEmployees;
	}
	
	
}
