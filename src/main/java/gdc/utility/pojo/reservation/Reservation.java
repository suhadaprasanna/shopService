/**
 * 
 */
package gdc.utility.pojo.reservation;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suhada
 *
 */
public class Reservation implements Serializable {

	private long id;
	private long traceId;
	private Date createDate;
	private String bookingNumber;
	private String description;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @return the traceId
	 */
	public long getTraceId() {
		return traceId;
	}

	/**
	 * @param traceId the traceId to set
	 */
	public void setTraceId(long traceId) {
		this.traceId = traceId;
	}

	/**
	 * @return the bookingNumber
	 */
	public String getBookingNumber() {
		return bookingNumber;
	}

	/**
	 * @param bookingNumber the bookingNumber to set
	 */
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
