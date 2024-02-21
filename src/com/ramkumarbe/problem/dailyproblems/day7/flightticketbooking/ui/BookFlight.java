package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.ui;

import java.util.ArrayList;
import java.util.List;

import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Flight;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Passenger;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.viewmodel.BookFlightViewModel;

public class BookFlight extends BaseView{

	private BookFlightViewModel viewModel;
	
	public BookFlight() {
		viewModel = new BookFlightViewModel(this);
	}

	public void bookFlight() {
		System.out.print("From Station: ");
		String fromStation = getString();
		System.out.print("To Station: ");
		String toStation = getString();
		List<Flight> flights = viewModel.getAvailableFlights(fromStation, toStation);
		showAvailableFlights(flights);
		System.out.print("Enter Flight Number: ");
		int flightNumber = getInt();
		Flight flight = getFlight(flights, flightNumber);
		List<Passenger> passengers = getPassengers();
		System.out.println("Total Fare: "+passengers.size()*flight.getFare());
		System.out.println("1.Pay    2.Cancel    3.Reschedule");
		int choice = getInt(); 
		if(choice == 1) {
			viewModel.bookTicket(flight, passengers, fromStation, toStation);
			System.out.println("Ticket Booked Successfully.");
		}
		else if(choice == 2) {
			System.out.println("Ticket booking has been cancelled.");
		}
	}

	private List<Passenger> getPassengers() {
		List<Passenger> passengers = new ArrayList<>();
		System.out.print("Enter Number of Passengers: ");
		int numberOfPassengers = getInt();
		for(int i=1; i<=numberOfPassengers; i++) {
			System.out.println("Enter Passenger "+i+" details:");
			System.out.print("Name: ");
			String name = getString();
			System.out.print("Age: ");
			int age = getInt();
			System.out.print("Gender: ");
			String gender = getString();
			System.out.print("ID: ");
			int id = getInt();
			passengers.add(new Passenger(id, name, age, gender, "CNF"));
		}
		return passengers;
	}

	private Flight getFlight(List<Flight> flights, int flightNumber) {
		for(Flight flight:flights) {
			if(flight.getFlightNumber() == flightNumber) {
				return flight;
			}
		}
		return null;
	}

	private void showAvailableFlights(List<Flight> flights) {
		System.out.println("Available flights");
		for(Flight flight:flights) {
			System.out.println("Flight Number: "+flight.getFlightNumber());
			System.out.println("Flight Name: "+flight.getFlightName());
			System.out.println("Departure Time: "+flight.getDepartureTime());
			System.out.println("Arrival Time: "+flight.getArrivalTime());
			System.out.println("Fare: "+flight.getFare());
			System.out.println("Seats: "+flight.getTotalSeats());
		}
	}
}
