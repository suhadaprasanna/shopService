/**
 * 
 */
package gdc.shop.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suhada
 *
 */
public class UserDTO implements Serializable{

	private long id;
	private String status;
	private long person_id;
	private PersonDTO person;
	private Date created_date;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getPerson_id() {
		return person_id;
	}
	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}
	public PersonDTO getPerson() {
		return person;
	}
	public void setPerson(PersonDTO person) {
		this.person = person;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	
}
