package com.techelevator.campground.model;

public class Site {

	private Long id;
	private Long camp_id;
	private int max_occupancy;
	private boolean accessible;
	private int max_rv_length;
	private boolean utilities;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCamp_id() {
		return camp_id;
	}
	public void setCamp_id(Long camp_id) {
		this.camp_id = camp_id;
	}
	public int getMax_occupancy() {
		return max_occupancy;
	}
	public void setMax_occupancy(int max_occupancy) {
		this.max_occupancy = max_occupancy;
	}
	public boolean isAccessible() {
		return accessible;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	public int getMax_rv_length() {
		
		return max_rv_length;
	}
	public void setMax_rv_length(int max_rv_length) {
		this.max_rv_length = max_rv_length;
	}
	public boolean isUtilities() {
		return utilities;
	}
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	public String isAccessibleString() {
		return (accessible)? "Yes" : "No";
	}
	public String isUtilitiesString() {
		return (utilities)? "Yes" : "N/A";
	}
	@Override
	public String toString() {
		return String.format("%-10s %-10s %-15s %-15d %-15s %-15s",getId(),getMax_occupancy(),isAccessibleString(), getMax_rv_length(),isUtilitiesString(), "$$$");
	}
	
	

}
