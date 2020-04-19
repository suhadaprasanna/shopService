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
public class UserForm extends Form{

	private long id;
	private long person;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	
	
}
