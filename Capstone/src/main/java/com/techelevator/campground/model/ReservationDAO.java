package com.techelevator.campground.model;

import java.time.LocalDate;

public interface ReservationDAO {
	public Long addReservation(Long siteId, String name, LocalDate startDate, int numDays, LocalDate dateCreated);
}
