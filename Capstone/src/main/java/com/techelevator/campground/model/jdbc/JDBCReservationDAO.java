package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Long addReservation(Long siteId, String name, LocalDate startDate, int numDays, LocalDate dateCreated) {
		Long reservationId = 0L;
		String sqlInsert = "INSERT INTO reservation (site_id, name, start_date, num_days, create_date) " +
							"VALUES (?,?,?,?,?);";
		jdbcTemplate.update(sqlInsert, siteId, name, startDate, numDays, dateCreated);
		String sqlReservationId = "SELECT reservation_id " +
									"FROM reservation " + 
									"WHERE create_date = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlReservationId, dateCreated);
		if (results.next()) {
			reservationId = results.getLong("reservation_id");
		}
		return reservationId;
	}
	
}
