package tn.suvis.production.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.suvis.production.entities.Production;

public class ProductionRowMapper implements RowMapper<Production>{

	@Override
	public Production mapRow(ResultSet rs, int rowNum) throws SQLException {
		Production p= new Production(rs.getDate("date"), rs.getLong("idSegment"), rs.getLong("idChaine"), rs.getDouble("qteProduite"),rs.getString("post"));
	p.setIdProduction(rs.getLong("IdProduction"));
	return p;
	}

}
