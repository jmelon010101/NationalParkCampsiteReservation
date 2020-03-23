package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Campground {

	private Long id;
	private Long park_id;
	private String name;
	private int open_from_mm;
	private int open_to_mm;
	private BigDecimal daily_fee;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPark_id() {
		return park_id;
	}
	public void setPark_id(Long park_id) {
		this.park_id = park_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOpen_from_mm() {
		return open_from_mm;
	}
	public void setOpen_from_mm(int open_from_mm) {
		this.open_from_mm = open_from_mm;
	}
	public int getOpen_to_mm() {
		return open_to_mm;
	}
	public void setOpen_to_mm(int open_to_mm) {
		this.open_to_mm = open_to_mm;
	}
	public BigDecimal getDaily_fee() {
		return daily_fee;
	}
	public void setDaily_fee(BigDecimal daily_fee) {
		this.daily_fee = daily_fee;
	}
	
	@Override
	public String toString() {
		String toMonth = convertToMonth(getOpen_to_mm());
		String fromMonth = convertToMonth(getOpen_from_mm());

		return String.format("#%d %s %s %s $%.2f",getId(),getName(),fromMonth,toMonth,getDaily_fee());
	}
	
	private String convertToMonth(int monthNumber) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "January");
		map.put(2, "February");
		map.put(3, "March");
		map.put(4, "April");
		map.put(5, "May");
		map.put(6, "June");
		map.put(7, "July");
		map.put(8, "August");
		map.put(9, "September");
		map.put(10, "October");
		map.put(11, "November");
		map.put(12, "December");
		
		return map.get(monthNumber);


	}
	
}

