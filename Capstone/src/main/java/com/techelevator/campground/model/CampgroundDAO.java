package com.techelevator.campground.model;

import java.util.List;

public interface CampgroundDAO {

	public List<Campground> getAllCampgroundsByParkId(Long park_id);
	
	//public List<Campground> getAvailableCampgrounds();
	
}
