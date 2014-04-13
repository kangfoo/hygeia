package org.springframework.batch.sample;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-10
 * Time: 上午12:31
 * 接口用于完成相应业务处理
 */
public class MessagesItemProcessor implements ItemProcessor<User, Message> {

    public Message process(User user) throws Exception {
//        Message m = new Message();
//        m.setContent("Hello " + user.getName()
//                + ",please pay promptly at the end of this month.");
//        return m;

        /*
        * 8.2 非空判断
        * */
        if(!StringUtils.hasText(user.getName())){
            throw new RuntimeException("The user name is required!");
        }
        Message m = new Message();
        m.setUser(user);
        m.setContent("Hello " + user.getName()
                + ",please pay promptly at end of this month.");
        return m;
    }

}
