package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto;

import java.util.List;

public class Flight {
	private int flightNumber;
	private String flightName;
	private String arrivalTime;
	private String departureTime;
	private List<String> routes;
	private int totalSeats;
	private int fare;

	public Flight(int flightNumber, String flightName, String arrivalTime, 
			String departureTime, int totalSeats, int fare, List<String> routes) {
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.routes = routes;
		this.totalSeats = totalSeats;
		this.fare = fare;
	}
	
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public List<String> getFlightRoutes() {
		return routes;
	}
	public void setFlightRoutes(List<String> flightRoutes) {
		this.routes = flightRoutes;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
}
