package org.springframework.batch.sample;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-9
 * Time: 下午4:20
 * 此代码来自于 http://www.ibm.com/developerworks/cn/java/j-lo-springbatch1/
 */
public class User {
//    private String name;
//    private Integer age;
//    public String getName() {return name;}
//    public void setName(String name) {this.name = name;}
//    public Integer getAge() {return age;}
//    public void setAge(Integer age) {this.age = age;}

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
