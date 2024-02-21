package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Flight;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Passenger;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Ticket;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.repository.Repository;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.ui.BookFlight;

public class BookFlightViewModel {

	private int pnrNumber = 5001;
	private BookFlight bookFlight;
	public BookFlightViewModel(BookFlight bookFlight) {
		this.bookFlight = bookFlight;
	}
	public List<Flight> getAvailableFlights(String fromStation, String toStation) {
		List<Flight> flights = new ArrayList<>();
		boolean fromPresent=false, toPresent=false;
		for(Flight flight:Repository.getInstance().getFligts()) {
			for(String route:flight.getFlightRoutes()) {
				if(route.equals(fromStation))  fromPresent = true;
				if(route.equals(toStation))  toPresent = true;
			}
			if(fromPresent && toPresent) {
				flights.add(flight);
			}
		}
		return flights;
	}
	public void bookTicket(Flight flight, List<Passenger> passengers, String fromStation, String toStation) {
		List<Integer> passengerIds = new ArrayList<>();
		for(Passenger passenger:passengers) {
			Repository.getInstance().getPassengers().add(passenger);
			passengerIds.add(passenger.getPassengerId());
		}
		Ticket ticket = new Ticket(pnrNumber++, flight.getFlightNumber(), passengerIds, fromStation, toStation);
		Repository.getInstance().getTickets().add(ticket);
	}

}
