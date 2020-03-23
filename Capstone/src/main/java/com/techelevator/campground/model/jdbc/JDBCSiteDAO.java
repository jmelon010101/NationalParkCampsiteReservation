package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<Site> getAvailableSites(Campground camgroundChoice, LocalDate arrivalDate, LocalDate departureDate){
		List<Site> availableList = new ArrayList<>();
		String sqlString ="SELECT site_id, max_occupancy, accessible, max_rv_length, utilities " + 
				"FROM site " + 
				"WHERE site_id NOT IN (SELECT site_id " + 
				"FROM reservation " + 
				"WHERE (start_date, start_date+num_days) OVERLAPS (?,?) AND campground_id= ?) "
				+ " LIMIT 5;";
		SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sqlString, arrivalDate, departureDate,camgroundChoice.getId());
		while (resultSet.next()) {
			Site s = new Site();
			s.setId(resultSet.getLong("site_id"));
			s.setMax_occupancy(resultSet.getInt("max_occupancy"));
			s.setAccessible(resultSet.getBoolean("accessible"));
			s.setMax_rv_length(resultSet.getInt("max_rv_length"));
			s.setUtilities(resultSet.getBoolean("utilities"));
			availableList.add(s);
		}
		return availableList;
	}
}
