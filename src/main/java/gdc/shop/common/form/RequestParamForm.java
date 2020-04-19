/**
 * 
 */
package gdc.shop.common.form;

/**
 * @author suhada
 *
 */
public class RequestParamForm {

	/* for pagination */
	private int start;
	private int count;
	// ase or desc
	private String order;
	/*--------------------------------------------------------------------*/

	/* for response arc*/
	private String ctype;
	/*--------------------------------------------------------------------*/
	
	/* get with other details in other services */
	private boolean with_person_details=false;
	/*--------------------------------------------------------------------*/
	
	/**/
	private long id;
	private long shop_id;
	/*--------------------------------------------------------------------*/
	
	private String username;
	private String password;
	
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public boolean isWith_person_details() {
		return with_person_details;
	}
	public void setWith_person_details(boolean with_person_details) {
		this.with_person_details = with_person_details;
	}
	public long getShop_id() {
		return shop_id;
	}
	public void setShop_id(long shop_id) {
		this.shop_id = shop_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	
}
