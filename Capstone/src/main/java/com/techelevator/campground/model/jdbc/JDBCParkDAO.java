package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.omg.CORBA.WStringSeqHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> list = new ArrayList<>();
		String sql = "SELECT * FROM park;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			list.add(createParkObject(results));
		}
		return list;
	}

	@Override
	public String getParkInfoByName(String name) {
		Park park = new Park();
		String sql = "SELECT location, establish_date, area, visitors, description " + "FROM park " + "WHERE name = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
		while (results.next()) {
			park.setName(name);
			park.setArea(results.getInt("area"));
			park.setVisitors(results.getInt("visitors"));
			park.setDescription(results.getString("description"));
			park.setLocation(results.getString("location"));
			park.setEstab_date(results.getDate("establish_date").toLocalDate());
		}
		String fullDescription = String.format(
				"Park Information Screen \n %s\n Location:"
						+ " %s \n Established: %s \n Area: %d sq km \n Annual Visitors: %d\n %s",
				park.getName(), park.getLocation(),
				park.getEstab_date().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), park.getArea(),
				park.getVisitors(), park.getDescription());

		return fullDescription;
	}

	private static Park createParkObject(SqlRowSet rows) {
		Park p = new Park();
		p.setId(rows.getLong("park_id"));
		p.setName(rows.getString("name"));
		p.setArea(rows.getInt("area"));
		p.setLocation(rows.getString("location"));
		p.setVisitors(rows.getInt("visitors"));
		p.setEstab_date(rows.getDate("establish_date").toLocalDate());
		p.setDescription(rows.getString("description"));
		return p;
	}

}
