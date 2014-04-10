package org.springframework.batch.sample;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-10
 * Time: 上午12:31
 * 接口用于完成相应业务处理
 */
public class MessagesItemProcessor implements ItemProcessor<User, Message> {

    public Message process(User user) throws Exception {
        Message m = new Message();
        m.setContent("Hello " + user.getName()
                + ",please pay promptly at the end of this month.");
        return m;
    }

}
