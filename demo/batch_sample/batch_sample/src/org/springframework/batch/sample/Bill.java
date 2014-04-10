/**
 * 
 */
package org.springframework.batch.sample;

/**
 * @author kunrey
 * 
 */
public class Bill {
	private Long id;
	private User user;
	private Double fees;
	private Double paidFees;
	private Double unpaidFees;
	private int payStatus;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}
	public Double getFees() {return fees;}
	public void setFees(Double fees) {this.fees = fees;}
	public Double getPaidFees() {return paidFees;}
	public void setPaidFees(Double paidFees) {this.paidFees = paidFees;}
	public Double getUnpaidFees() {return unpaidFees;}
	public void setUnpaidFees(Double unpaidFees) {this.unpaidFees = unpaidFees;}
	public int getPayStatus() {return payStatus;}
	public void setPayStatus(int payStatus) {this.payStatus = payStatus;}
}
