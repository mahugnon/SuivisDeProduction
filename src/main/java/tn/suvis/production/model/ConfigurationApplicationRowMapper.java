package tn.suvis.production.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.suvis.production.entities.ConfigurationApplication;

public class ConfigurationApplicationRowMapper implements RowMapper<ConfigurationApplication>{

	@Override
	public ConfigurationApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfigurationApplication c=new ConfigurationApplication();
		c.setEscale1(rs.getInt("escale1"));
		c.setEscale2(rs.getInt("escale2"));
		c.setEscale3(rs.getInt("escale3"));
		c.setId(rs.getLong("id"));
		return c;
	}

}
