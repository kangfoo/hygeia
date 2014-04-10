/**
 * 
 */
package org.springframework.batch.sample;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author kunrey
 *
 */
public class UserDbMapper implements RowMapper {
	
	public Object mapRow(ResultSet rs, int i) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("ID"));
		user.setName(rs.getString("NAME"));
		user.setAge(rs.getInt("AGE"));
		user.setBalance(rs.getDouble("BALANCE"));
		return user;
	}

}
