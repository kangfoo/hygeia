package org.springframework.batch.sample;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-12
 * Time: 下午9:54
 * To change this template use File | Settings | File Templates.
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
