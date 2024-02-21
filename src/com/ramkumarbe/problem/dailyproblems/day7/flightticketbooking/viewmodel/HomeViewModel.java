package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.viewmodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Flight;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Passenger;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Ticket;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.repository.Repository;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.ui.Home;

public class HomeViewModel {

	private Home home;
	private Repository repository;
	public HomeViewModel(Home home) {
		this.home = home;
		this.repository = Repository.getInstance();
		loadData();
	}
	
	private void loadData() {
		try {
			repository.loadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Ticket getTicket(int pnrNumber) {
		for(Ticket ticket:repository.getTickets()) {
			if(ticket.getPNRnumber()==pnrNumber) {
				return ticket;
			}
		}
		return null;
	}

	public Flight getFlight(Ticket ticket) {
		return getFlight(ticket.getFlightNumber());
	}

	public List<Passenger> getTicket(Ticket ticket) {
		List<Passenger> passengers = new ArrayList<>();
		for(Passenger passenger:repository.getPassengers()) {
			for(int passengerId:ticket.getPassengerIds()) {
				if(passenger.getPassengerId()==passengerId) {
					passengers.add(passenger);
				}
			}
		}
		return passengers;
	}

	public void reloadData() {
		try {
			repository.reloadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean cancelTicket(int pnrNumber) {
		int index=-1, i=0;
		for(Ticket ticket:repository.getTickets()) {
			if(ticket.getPNRnumber()==pnrNumber) {
				index = i;
				break;
			}
			i++;
		}
		if(index==-1) {
			return false;
		}
		repository.getTickets().remove(index);
		return true;
	}

	public List<Ticket> getBookedTickets() {
		return repository.getTickets();
	}

	public void changeStatus(List<Integer> passengerIds, String status) {
		for(int id:passengerIds) {
			getPassenger(id).setStatus(status);	
		}
	}

	public Passenger getPassenger(int passengerId) {
		for(Passenger passenger:repository.getPassengers()) {
			if(passenger.getPassengerId()==passengerId) {
				return passenger;
			}
		}
		return null;
	}

	public List<Flight> getFlights() {
		return repository.getFligts();
	}

	public Flight getFlight(int flightNumber) {
		for(Flight flight:repository.getFligts()) {
			if(flight.getFlightNumber()==flightNumber) {
				return flight;
			}
		}
		return null;
	}
	
}
