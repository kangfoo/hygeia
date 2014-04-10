package org.springframework.batch.sample;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-9
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
public class UserMapper implements FieldSetMapper<User>{
    @Override
    public User mapFieldSet(FieldSet fs) throws BindException {
        User u = new User();
        u.setName(fs.readString(0));
        u.setAge(fs.readInt(1));
        return u;
    }
}
