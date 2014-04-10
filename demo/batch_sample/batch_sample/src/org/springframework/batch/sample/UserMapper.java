/**
 * 
 */
package org.springframework.batch.sample;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class UserMapper implements FieldSetMapper<User> {
	public User mapFieldSet(FieldSet fs) throws BindException {
		User u = new User();
		u.setName(fs.readString(0));
		u.setAge(fs.readInt(1));
		return u;
	}
}
