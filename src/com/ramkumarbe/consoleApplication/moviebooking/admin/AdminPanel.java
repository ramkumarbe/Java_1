package com.ramkumarbe.consoleApplication.moviebooking.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ramkumarbe.consoleApplication.moviebooking.BaseView;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Movie;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Show;
import com.ramkumarbe.consoleApplication.moviebooking.login.Login;

public class AdminPanel extends BaseView{

    private AdminPanelViewModel viewModel;

    public AdminPanel() {
        viewModel = new AdminPanelViewModel(this);
    }

    public void adminHome() {
        int choice;
        do {
            printMenu();
            choice = getInt();
            switch (choice) {
                case 1 -> addMovie();
                case 2 -> addShow();
                case 3 -> new Login().login();
                case 0 -> {
                    System.out.println("Exiting..");
                    System.exit(0);
                }
            }
        } while (choice != 0);
    }

    private void printMenu() {
        int menuWidth = 40;

        System.out.println("+" + repeatChar('-', menuWidth - 2) + "+");
        System.out.println("| " + centerText("Menu", menuWidth - 4) + " |");
        System.out.println("+" + repeatChar('-', menuWidth - 2) + "+");
        System.out.println("| " + centerText("1. Add movie", menuWidth - 4) + " |");
        System.out.println("| " + centerText("2. Add show", menuWidth - 4) + " |");
        System.out.println("|" + centerText("3. Logout", menuWidth - 3) + " |");
        System.out.println("|" + centerText("4. Exit", menuWidth - 3) + " |");
        System.out.println("+" + repeatChar('-', menuWidth - 2) + "+");
    }

    private String repeatChar(char ch, int count) {
        return new String(new char[count]).replace('\0', ch);
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return repeatChar(' ', padding) + text + repeatChar(' ', width - text.length() - padding);
    }

    private void addShow() {
        System.out.print("Enter the Date(dd-mm-yyyy HH:MM): ");
        String dateTimeString = getString();
        LocalDateTime dateTime = viewModel.getDateTime(dateTimeString);
        if(dateTime==null) {
        	return;
        }
        List<Movie> movies = viewModel.getMovies();
        if(movies ==null) {
        	return;
        }
        Movie movie = selectMovie(movies);
        if(movie==null) {
        	return;
        }
        Show show = new Show(dateTime, movie);
        viewModel.uploadShow(show);
    }

    private Movie selectMovie(List<Movie> movies) {
        Map<Integer, Movie> moviesList = new HashMap<>();
        int i = 1;
        for (Movie movie : movies) {
            moviesList.put(i, movie);
            System.out.println(i++ + ". " + movie.getTitle());
        }
        System.out.println("Select the movie: ");
        int selection = getInt();
        if(selection>movies.size()) {
        	System.out.println("Enter valid Input.");
        	return null;
        }
        return moviesList.get(selection);
    }

    private void addMovie() {
        System.out.print("Enter the movie name: ");
        String movieName = getString();
        System.out.print("Enter the director name: ");
        String director = getString();

        List<String> genres = getGenresList();
        System.out.print("Enter the price of the movie: ");
        int priceOfTheMovie = getInt();

        Movie movie = new Movie(movieName, director, genres, priceOfTheMovie);
        viewModel.addMovie(movie);
    }

    private List<String> getGenresList() {
        Map<Integer, String> genresList = viewModel.getGenres();
        List<String> genres = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            System.out.println(i + ". " + genresList.get(i));
        }
        System.out.print("Enter the number of genres: ");
        int n = getInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the choice: ");
            genres.add(genresList.get(getInt()));
        }
        return genres;
    }

	void printMessage(String message) {
		System.out.println(message);
	}
}
