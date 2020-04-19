/**
 * 
 */
package gdc.shop.common.form;

/**
 * @author suhada
 *
 */
public class EmployeeRoleForm extends Form{
	
	private int id;
	private String status;
	private String role_name;
	private String role_code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
}
