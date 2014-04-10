package org.springframework.batch.sample;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-10
 * Time: 上午12:29
 * 写数据
 */
public class MessagesItemWriter implements ItemWriter<Message> {
    public void write(List<? extends Message> messages) throws Exception {
        System.out.println("write results");
        for (Message m : messages) {
            System.out.println(m.getContent());
        }
    }
}
