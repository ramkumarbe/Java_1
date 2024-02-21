package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.ui;

import java.util.List;

import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Flight;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Passenger;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Ticket;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.viewmodel.HomeViewModel;

public class Home extends BaseView{
	
	private HomeViewModel viewModel;
	
	public Home() {
		viewModel = new HomeViewModel(this);
	}
	
	public void menu() {
		System.out.println("Welcome to Indian Airway");
		int choice;
		do {
			printMenu();
			choice = getInt();
			switch(choice) {
		    case 1 -> {
		    	bookFlight();
		    }
		    case 2 -> {
		    	getPNRStatus();
		    }
		    case 3 -> {
		    	getBookedTickets();
		    }
		    case 4 -> {
		    	cancelTickets();
		    }
		    case 5 -> {
		    	searchPassengers();
		    }
		    case 6 -> {
		    	changeTicketStatus();
		    }
		    case 7 -> {
		    	listFlightRoutes();
		    }
		    case 8 -> {
		    	addFlightRoutes();
		    }
		    case 0 -> {
		    	System.out.println("Exiting...");
		    }
			}
		}while(choice != 0);
		
		viewModel.reloadData();
	}

	private void addFlightRoutes() {
		System.out.print("Enter Flight number: ");
		int flightNumber = getInt();
		Flight flight = viewModel.getFlight(flightNumber);
		printFlightRoutes(flight);
		System.out.println("Enter new Route: ");
		String newRoute = getString();
		flight.getFlightRoutes().add(newRoute);
	}

	private void listFlightRoutes() {
		for(Flight flight:viewModel.getFlights()) {
			printFlightRoutes(flight);
		}
	}

	private void printFlightRoutes(Flight flight) {
		System.out.println(flight.getFlightName());
		int i=0;
		for(String route:flight.getFlightRoutes()) {
			System.out.println("\t"+ ++i +". "+route);
		}
	}

	private void searchPassengers() {
		System.out.print("Enter Passenger ID: ");
		int passengerId = getInt();
		Passenger passenger = viewModel.getPassenger(passengerId);
		if(passenger==null) {
			System.out.println("No such passenger available.");
			return;
		}
		printPassenger(passenger);
	}

	private void changeTicketStatus() {
		System.out.print("Enter PNR Number: ");
		int pnrNumber = getInt();
		Ticket ticket = viewModel.getTicket(pnrNumber);
		System.out.println("Enter Ticket Status: ");
		System.out.println("1.CNF    2.CANCEL");
		int choice = getInt();
		if(choice==1) {
			viewModel.changeStatus(ticket.getPassengerIds(), "CNF");
		}
		else {
			viewModel.changeStatus(ticket.getPassengerIds(), "CANCEL");
		}
		System.out.println("Ticket status has been changed.");
	}

	private void cancelTickets() {
		System.out.print("Enter the PNR Number: ");
		int pnrNumber = getInt();
		boolean flag = viewModel.cancelTicket(pnrNumber);
		if(flag)
			System.out.println("Your Ticket has been cancelled.");
		else {
			System.out.println("No such Ticket is booked.");
		}
	}

	private void getBookedTickets() {
		for(Ticket ticket:viewModel.getBookedTickets()) {
			System.out.println("PNR Number: "+ticket.getPNRnumber());
			System.out.println("Flight Number: "+ticket.getFlightNumber());
			System.out.println("Passenger Ids: "+ticket.getPassengerIds());
			System.out.println("From: "+ticket.getFrom());
			System.out.println("To: "+ticket.getTo());
		}
	}

	private void getPNRStatus() {
		System.out.print("Enter PNR Number: ");
		int pnrNumber = getInt();
		Ticket ticket = viewModel.getTicket(pnrNumber);
		if(ticket == null) {
			System.out.println("No such Ticket is available.");
			return;
		}
		System.out.println("Ticket Details:");
		System.out.println("Flight Details:");
		Flight flight = viewModel.getFlight(ticket);
		printFlightDetails(flight);
		List<Passenger> passengers = viewModel.getTicket(ticket);
		System.out.println("Passenger Details:");
		printPassengerDetails(passengers);
	}

	private void printPassengerDetails(List<Passenger> passengers) {
		for(Passenger passenger:passengers) {
			printPassenger(passenger);
		}
	}

	private void printPassenger(Passenger passenger) {
		System.out.println("Name: "+passenger.getName());
		System.out.println("Age: "+passenger.getAge());
		System.out.println("Gender: "+passenger.getGender());
		System.out.println("Id: "+passenger.getPassengerId());
		System.out.println("Status: "+passenger.getStatus());
	}

	private void printFlightDetails(Flight flight) {
		System.out.println("Flight Number: "+flight.getFlightNumber());
		System.out.println("Flight Name: "+flight.getFlightName());
		System.out.println("Departure Time: "+flight.getDepartureTime());
		System.out.println("Arrival Time: "+flight.getArrivalTime());
		System.out.println("Fare: "+flight.getFare());
		System.out.println("Seats: "+flight.getTotalSeats());
	}

	private void bookFlight() {
		BookFlight bookFlight = new BookFlight();
		bookFlight.bookFlight();
	}

	private void printMenu() {
		System.out.println("Enter your option :");
		System.out.println("1-Booking");
		System.out.println("2-Get PNR status");
		System.out.println("3-Booked tickets");
		System.out.println("4-Cancel Tickets");
		System.out.println("5-Search passenger");
		System.out.println("6-Change ticket status of a passenger");
		System.out.println("7-List flight routes");
		System.out.println("8-Add flight");
		System.out.println("0-Exit");
	}
}
