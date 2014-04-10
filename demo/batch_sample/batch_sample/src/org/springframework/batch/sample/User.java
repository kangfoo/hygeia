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
 * @create-time ����12:21:50
 * @revision    $Id$
 *
 ***********************************************************************/
package org.springframework.batch.sample;

public class User {
	private Long id;
	private String name;
	private Integer age;
	private Double balance;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public Integer getAge() {return age;}
	public void setAge(Integer age) {this.age = age;}
	public Double getBalance() {return balance;}
	public void setBalance(Double balance) {this.balance = balance;}
}
