package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto;

import java.util.List;

public class Ticket {

	private int PNRnumber;
	private int flightNumber;
	private List<Integer> passengerIds;
	private String from;
	private String to;
	private Flight flight;
	private List<Passenger> passengers;
	
	public Ticket(int pNRnumber, int flightNumber, List<Integer> passengerIds, String from, String to) {
		PNRnumber = pNRnumber;
		this.flightNumber = flightNumber;
		this.passengerIds = passengerIds;
		this.from = from;
		this.to = to;
	}
	public int getPNRnumber() {
		return PNRnumber;
	}
	public void setPNRnumber(int pNRnumber) {
		PNRnumber = pNRnumber;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public List<Integer> getPassengerIds() {
		return passengerIds;
	}
	public void setPassengerIds(List<Integer> passengerIds) {
		this.passengerIds = passengerIds;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String destination) {
		this.to = destination;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public List<Passenger> getPassenger() {
		return passengers;
	}
	public void setPassenger(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String airPort) {
		this.from = airPort;
	}
}
