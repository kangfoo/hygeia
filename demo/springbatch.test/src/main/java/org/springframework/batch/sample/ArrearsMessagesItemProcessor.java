package org.springframework.batch.sample;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-12
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
public class ArrearsMessagesItemProcessor implements
        ItemProcessor<Bill, Message> {

    public Message process(Bill item) throws Exception {
        if (item.getPayStatus() == 0) {/*unpaid*/
            Message m = new Message();
            m.setUser(item.getUser());
            m.setContent("Hello " + item.getUser().getName()
                    + ",please pay promptly at end of this month.");
            return m;
        }
        return null;
    }

}
