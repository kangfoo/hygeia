/**
 * 
 */
package org.springframework.batch.sample;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author kunrey
 * 
 */
public class BillingItemProcessor implements ItemProcessor<User, Bill> {

	public Bill process(User item) throws Exception {
		Bill b = new Bill();
		b.setUser(item);
		b.setFees(70.00);
		b.setPaidFees(0.0);
		b.setUnpaidFees(70.00);
		b.setPayStatus(0);/*unpaid*/
		return b;
	}

}
