package com.techelevator.campground.model;

import java.time.LocalDate;

public class Reservation {

	private Long id;
	private Long site_id;
	private String name;
	private LocalDate start_date;
	private int num_of_days;
	private LocalDate create_date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSite_id() {
		return site_id;
	}
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public int getNum_of_days() {
		return num_of_days;
	}
	public void setNum_of_days(int num_of_days) {
		this.num_of_days = num_of_days;
	}
	public LocalDate getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDate create_date) {
		this.create_date = create_date;
	}

	
}
