/**
 * 
 */
package gdc.shop.common.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author suhada
 *
 */
public class ShopEmployeeForm extends Form{

	private long id;
	private long shop_id;
	private String username;
	private String password;
	private int employee_role_id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date register_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sys_add_date;
	private String status;
	private long user_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getShop_id() {
		return shop_id;
	}
	public void setShop_id(long shop_id) {
		this.shop_id = shop_id;
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
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public int getEmployee_role_id() {
		return employee_role_id;
	}
	public void setEmployee_role_id(int employee_role_id) {
		this.employee_role_id = employee_role_id;
	}
	
	
}
