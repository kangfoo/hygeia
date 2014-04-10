/***********************************************************************
 *
 * û������
 *
 * batch_sample 1.0Դ���뿽��Ȩ�������Ĵ�ʱ������������޹�˾���У�
 * �ܵ����ɵı������κι�˾����ˣ�δ����Ȩ�������Կ�����
 *
 * @copyright   Copyright: 2002-2006 Beijing Startimes
 *              Software Technology Co. Ltd.
 * @creator     liugr liugr@startimes.com.cn <br/>
 * @create-time ����12:28:03
 * @revision    $Id$
 *
 ***********************************************************************/
package org.springframework.batch.sample;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class MessagesItemWriter implements ItemWriter<Message>{

	public void write(List<? extends Message> messages) throws Exception {
		System.out.println("write results");
		for (Message m : messages) {
			System.out.println(m.getContent());
		}
	}
}
