package com.techelevator.campground.model;

import java.util.List;

public interface ParkDAO {

	//returns all parks for main menu (id, name)
	public List<Park> getAllParks();
	
	//returns all info from selected park (name, location, established, area, visitors)
	public String getParkInfoByName(String name);
	
}
