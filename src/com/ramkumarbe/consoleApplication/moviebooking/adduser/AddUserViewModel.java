package com.ramkumarbe.consoleApplication.moviebooking.adduser;

import java.sql.SQLException;

import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.repository.MovieRepository;

public class AddUserViewModel {
	AddUser addUserView;

	public AddUserViewModel(AddUser addUser) {
		this.addUserView = addUser;
	}

	public boolean validateUserName(String userName) {
		if (!userName.matches("^[a-zA-Z0-9_.]+$")) {
			addUserView.printErrorMessage("Invalid Username");
			return false;
		}
		return true;
	}

	public boolean validatePassword(String password) {
		if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])([A-Za-z\\d@#$%^&+=!]){8,}$")) {
			addUserView.printErrorMessage(
					"Invalid Password - Password length must contain atleast 8 characters and must contain atleast 1 alphabet, 1 number, 1 special character.");
			return false;
		}
		return true;
	}

	public boolean validatePhoneNumber(String phoneNumber) {
		if (phoneNumber.matches("^(\\+\\d{1,3})?[6-9]\\d{9}$")) {
			return true;
		}
		addUserView.printErrorMessage("Invalid Mobile Number");
		return false;
	}

	public boolean validateMailId(String mailId) {
		if(!mailId.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")) {
			addUserView.printErrorMessage("Invalid Mail Id");
			return false;
		}
		return true;
	}

	public boolean addUser(User user) {
		try {
			if(MovieRepository.getInstance().isUserAvailable(user)) {
				MovieRepository.getInstance().addUser(user);
				addUserView.onSuccess();
				return true;
			}
			else {
				addUserView.printErrorMessage("Username already available");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			addUserView.onFailure();
			return false;
		}
	}
}
