package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Flight;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Passenger;
import com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto.Ticket;

public class Repository {

	private static Repository repository;
	private Repository() {  }
	
	public static Repository getInstance() {
		if(repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	private List<Flight> flights;
	private List<Passenger> passengers;
	private List<Ticket> tickets;
	
	public void loadData() throws IOException {
		readFlightsDetails();
		readPassengersDetails();
		readTicketsDetails();
	}
	private void readTicketsDetails() throws IOException {
		tickets = new ArrayList<>();
		File file = new File("C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\problem\\dailyproblems\\day7\\flightticketbooking\\Tickets.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		reader.readLine();
		while((line=reader.readLine())!=null) {
			String[] temp = line.split(",");
			int PNRNumber = Integer.parseInt(temp[0]);
			int flightNumber = Integer.parseInt(temp[1]);
			String airPort = temp[2];
			String destination = temp[3];
			List<Integer> passengerIds = new ArrayList<>();
			for(int i=4; i<temp.length; i++) {
				passengerIds.add(Integer.parseInt(temp[i].replaceAll("^\"|\"$", "")));
			}
			Ticket ticket = new Ticket(PNRNumber, flightNumber, passengerIds, airPort, destination);
			tickets.add(ticket);
		}
		reader.close();
	}

	private void readPassengersDetails() throws IOException {
		passengers = new ArrayList<>();
		File file = new File("C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\problem\\dailyproblems\\day7\\flightticketbooking\\Passengers.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		reader.readLine();
		while((line=reader.readLine())!=null) {
			String[] temp = line.split(",");
			System.out.println(Arrays.toString(temp));
			int passengerId = Integer.parseInt(temp[0]);
			String passengerName = temp[1];
			int age = Integer.parseInt(temp[2]);
			String gender = temp[3];
			String status = temp[4];
			Passenger passenger = new Passenger(passengerId, passengerName, age, gender, status);
			passengers.add(passenger);
		}
		reader.close();
	}

	private void readFlightsDetails() throws IOException {
		flights = new ArrayList<>();
		File file = new File("C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\problem\\dailyproblems\\day7\\flightticketbooking\\Flights.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		reader.readLine();
		while((line=reader.readLine())!=null) {
			String[] temp = line.split(",");
			int flightNumber = Integer.parseInt(temp[0]); 
			String flightName = temp[1];
			String arrivalTime = temp[2]; 
			String departureTime = temp[3]; 
			int totalSeats = Integer.parseInt(temp[4]);
			int fare = Integer.parseInt(temp[5]);
			List<String> routes = new ArrayList<>();
	        for (int i = 6; i < temp.length; i++) {
	            String route = temp[i].replaceAll("^\"|\"$", ""); 
	            routes.add(route);
	        }
	        Flight flight = new Flight(flightNumber, flightName, arrivalTime, departureTime, totalSeats, fare, routes);
			flights.add(flight);
		}
		reader.close();
	}

	public List<Flight> getFligts() {
		return flights;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void reloadData() throws IOException {
		writeFlightsDetails();
		writePassengersDetails();
		writeTicketsDetails();
	}

	private void writeTicketsDetails() throws IOException {
		File file = new File("C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\problem\\dailyproblems\\day7\\flightticketbooking\\Tickets.csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		writer.write("PNRnumber,FlightNumber,PassengerIds,From,To");
	    writer.newLine();
	    
		for (Ticket ticket : tickets) {
	        writer.write(ticket.getPNRnumber() + ",");
	        writer.write(ticket.getFlightNumber() + ",");
	        writer.write(ticket.getFrom() + ",");
	        writer.write(ticket.getTo() + ",");
	        writer.write(joinIds(ticket.getPassengerIds()));
	        writer.newLine();
	    }
		writer.close();
	}
	private String joinIds(List<Integer> ids) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < ids.size(); i++) {
	        sb.append(ids.get(i));
	        if (i < ids.size() - 1) {
	            sb.append(",");
	        }
	    }
	    return sb.toString();
	}
	
	private void writePassengersDetails() throws IOException {
	    File file = new File("C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\problem\\dailyproblems\\day7\\flightticketbooking\\Passengers.csv");
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    
	    writer.write("PassengerId,Name,Age,Gender,Status");
	    writer.newLine();
	    for (Passenger passenger : passengers) {
	        writer.write(passenger.getPassengerId() + ",");
	        writer.write(passenger.getName() + ",");
	        writer.write(passenger.getAge() + ",");
	        writer.write(passenger.getGender() + ",");
	        writer.write(passenger.getStatus());
	        writer.newLine();
	    }
	    writer.close();
	}


	private void writeFlightsDetails() throws IOException {
	    File file = new File("C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\problem\\dailyproblems\\day7\\flightticketbooking\\Flights.csv");
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write("FlightNumber,FlightName,ArrivalTime,DepartureTime,TotalSeats,Fare,Routes");
	    writer.newLine();
	    for (Flight flight : flights) {
	        writer.write(flight.getFlightNumber() + ",");
	        writer.write(flight.getFlightName() + ",");
	        writer.write(flight.getArrivalTime() + ",");
	        writer.write(flight.getDepartureTime() + ",");
	        writer.write(flight.getTotalSeats() + ",");
	        writer.write(flight.getFare() + ",");
	        writer.write(joinRoutes(flight.getFlightRoutes()));
	        writer.newLine();
	    }
	    writer.close();
	}

	private String joinRoutes(List<String> routes) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < routes.size(); i++) {
	        sb.append(routes.get(i));
	        if (i < routes.size() - 1) {
	            sb.append(",");
	        }
	    }
	    return sb.toString();
	}

	
}
