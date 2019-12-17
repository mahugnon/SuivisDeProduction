package tn.suvis.production.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.suvis.production.entities.Arret;

public class ArretRowMapper implements RowMapper<Arret>{

	@Override
	public Arret mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Arret(rs.getLong("id"),rs.getDate("debut"), rs.getDate("fin"),rs.getLong("idSegment"),rs.getLong("idChaine"),rs.getString("status"),rs.getString("raison"));
	}

}
