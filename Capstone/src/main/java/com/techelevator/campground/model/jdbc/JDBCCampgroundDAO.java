package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;

public class JDBCCampgroundDAO implements CampgroundDAO {
	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgroundsByParkId(Long park_id) {
		List<Campground> list = new ArrayList<>();
		String sqlString = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id = ?;";
		SqlRowSet results= jdbcTemplate.queryForRowSet(sqlString,park_id);
		while (results.next()) {
			Campground c = new Campground();
			c.setId(results.getLong("campground_id"));
			c.setPark_id(results.getLong("park_id"));
			c.setName(results.getString("name"));
			c.setOpen_from_mm(results.getInt("open_from_mm"));
			c.setOpen_to_mm(results.getInt("open_to_mm"));
			c.setDaily_fee(results.getBigDecimal("daily_fee"));
			list.add(c);
		}
		return list;
	}

}
