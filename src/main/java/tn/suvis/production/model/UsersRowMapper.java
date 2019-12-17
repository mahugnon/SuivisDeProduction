package tn.suvis.production.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.suvis.production.entities.Users;

public class UsersRowMapper implements RowMapper<Users>{

	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
Users u=new Users();
u.setUserName(rs.getString("user_name"));
u.setTelephone(rs.getString("telephone"));
		return u;
	}

}
