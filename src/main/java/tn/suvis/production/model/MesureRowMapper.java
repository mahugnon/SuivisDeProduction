package tn.suvis.production.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.suvis.production.entities.Mesure;

public class MesureRowMapper implements RowMapper<Mesure> {

	@Override
	public Mesure mapRow(ResultSet rs, int rowNum) throws SQLException {
Mesure mesure=new Mesure(rs.getLong("idSegment"), rs.getString("post"), rs.getLong("idChaine"), rs.getInt("etatArret"), rs.getString("type"), rs.getInt("tArret"), rs.getDouble("qteProduite"), rs.getDate("date"));
		return mesure;
	}

}
