/**
 * 
 */
package gdc.shop.datamanager.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author suhada
 *
 */
@Entity
@Table(name = "shop_employee", catalog = "gdc_shop")
public class ShopEmployee {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop", nullable = false)
	private Shop shop;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_role", nullable = false)
	private EmployeeRole employee_role;
	@Column(name = "username", nullable = false, length = 100)
	private String username;
	@Column(name = "password", nullable = false, length = 250)
	private String password;
	@Temporal(TemporalType.DATE)
	@Column(name = "register_date")
	private Date register_date;
	@Temporal(TemporalType.DATE)
	@Column(name = "sys_add_date")
	private Date sys_add_date;
	@Column(name = "status", nullable = false, length = 10)
	private String status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", nullable = false)
	private User user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public EmployeeRole getEmployee_role() {
		return employee_role;
	}
	public void setEmployee_role(EmployeeRole employee_role) {
		this.employee_role = employee_role;
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
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public Date getSys_add_date() {
		return sys_add_date;
	}
	public void setSys_add_date(Date sys_add_date) {
		this.sys_add_date = sys_add_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
