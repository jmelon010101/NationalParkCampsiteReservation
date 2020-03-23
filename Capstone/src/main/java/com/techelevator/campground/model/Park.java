package com.techelevator.campground.model;

import java.time.LocalDate;

public class Park {

	private Long id;
	private String name;
	private String location;
	private LocalDate estab_date;
	private int area;
	private int visitors;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDate getEstab_date() {
		return estab_date;
	}
	public void setEstab_date(LocalDate localDate) {
		this.estab_date = localDate;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getVisitors() {
		return visitors;
	}
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return this.getName();
	}
	
	
	
}
