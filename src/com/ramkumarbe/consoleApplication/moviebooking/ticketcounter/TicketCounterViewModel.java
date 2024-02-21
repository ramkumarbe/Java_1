package com.ramkumarbe.consoleApplication.moviebooking.ticketcounter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ramkumarbe.consoleApplication.moviebooking.bookticket.BookTicket;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Movie;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Show;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Ticket;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.repository.MovieRepository;

public class TicketCounterViewModel {

	private TicketCounter ticketCounter;
	private List<Show> showsList;
	private MovieRepository repository;

	public TicketCounterViewModel(TicketCounter ticketCounter) {
		this.ticketCounter = ticketCounter;
		repository = MovieRepository.getInstance();
		getConnection();
	}

	private void getConnection() {
        try {
        	repository.getConnection();
        	repository.getStatement();
        	repository.loadMovies();
        }catch(SQLException | ClassNotFoundException e){
        	handleSQLException(e, "Error loading movies.");
        }
	}

	public void viewBookedTickets(User user) {
		List<Ticket> bookedTickets = getBookedTickets(user);
		if (bookedTickets.isEmpty()) {
			ticketCounter.showMessage("No tickets booked yet.");
		} else {
			ticketCounter.printTickets(bookedTickets);
		}
	}

	public List<Movie> getMoviesList() {
		return repository.getAvailableMovies();
	}

	public Movie selectedMovie(int n) {
		if (n <= 0 || n > repository.getAvailableMovies().size()) {
			return null;
		}
		return repository.getAvailableMovies().get(n - 1);
	}

	public Ticket bookTicket(User currentUser, Show selectedShow, List<Integer> seats) {
		BookTicket bookTicket = new BookTicket();
		return bookTicket.bookTicket(currentUser, selectedShow, seats);
	}

	public Show getSelectedShow(int n) {
		if (n <= 0 || n > showsList.size()) {
			return null;
		}
		return showsList.get(n - 1);
	}

	public boolean confirmBooking(Ticket ticket) {
		if (ticket != null) {
			ticketCounter.showMessage("Ticket Booking was Successful.");
			return true;
		} else {
			ticketCounter.showMessage("Ticket Booking was not successful.");
			return false;
		}
	}

	public List<Show> getShowsList(Movie selectedMovie) {
		showsList = new ArrayList<>();
		try {
			showsList = repository.getShowsList(selectedMovie);
		} catch (SQLException e) {
			handleSQLException(e, "Error fetching shows list.");
		}
		return showsList;
	}

	public String[] getAvailableSeats(Show selectedShow) {
		try {
			return repository.getAvailableSeats(selectedShow);
		} catch (SQLException e) {
			handleSQLException(e, "Error fetching available seats.");
			return null;
		}
	}

	public List<Integer> getAvailableSeats(String[] seats, int numberOfSeats) {
		List<Integer> selectedSeats = new ArrayList<>();
		for (int i = 1; i <= numberOfSeats; i++) {
			int seatNumber = ticketCounter.getSeatNumber();
			if (seatNumber <= 0) {
				return null;
			}
			if (!seats[seatNumber - 1].equals("[B]")) {
				selectedSeats.add(seatNumber);
			} else {
				System.out.println("This seat is already booked.");
				i--;
			}
		}
		return selectedSeats;
	}

	private List<Ticket> getBookedTickets(User user) {
		try {
			return repository.getBookedTickets(user);
		} catch (SQLException e) {
			handleSQLException(e, "Error fetching booked tickets.");
			return new ArrayList<>();
		}
	}

	private void handleSQLException(Exception e, String errorMessage) {
		e.printStackTrace();
		ticketCounter.showMessage(errorMessage);
	}
}
