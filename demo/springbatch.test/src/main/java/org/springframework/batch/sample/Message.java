package org.springframework.batch.sample;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-9
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public class Message {
//    private String content;
//    public String getContent() {return content;}
//    public void setContent(String content) {this.content = content;}

    private Long id;
    private User user;
    private String content;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

}
