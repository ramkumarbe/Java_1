package com.ramkumarbe.consoleApplication.moviebooking.login;

import com.ramkumarbe.consoleApplication.moviebooking.BaseView;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;

public class Login  extends BaseView{

    private LoginPresenter presenter;

    public Login() {
        presenter = new LoginPresenter(this);
    }

    public User getUser(){
        User user = null;
        int choice;
        do {
            printMenu();
            choice = getInt();
            switch (choice) {
                case 1 -> user = login();
                case 2 -> user = signup();
                case 0 -> {
                    System.out.println("Exiting..");
                    System.exit(0);
                }
                default -> System.out.println("Enter valid input.");
            }
        } while ((choice != 1 && choice != 2) || user==null);
        return user;
    }

    private void printMenu() {
        int boxWidth = 25;

        System.out.println("+" + repeatChar('-', boxWidth - 2) + "+");
        System.out.println("| " + centerText("Menu", boxWidth - 4) + " |");
        System.out.println("+" + repeatChar('-', boxWidth - 2) + "+");
        System.out.println("| " + centerText("1. Login", boxWidth - 4) + " |");
        System.out.println("| " + centerText("2. Signup", boxWidth - 4) + " |");
        System.out.println("| " + centerText("0. Exit", boxWidth - 4) + " |");
        System.out.println("+" + repeatChar('-', boxWidth - 2) + "+");
        System.out.print("Enter your choice: ");
    }

    private String repeatChar(char ch, int count) {
        return new String(new char[count]).replace('\0', ch);
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return repeatChar(' ', padding) + text + repeatChar(' ', width - text.length() - padding);
    }


    public User signup() {
        return presenter.addNewUser();
    }

    public User login() {
        User user;
        do {
            System.out.print("Enter the UserName: ");
            String userName = getString();

            System.out.print("Enter the Password: ");
            String password = getString();
            user = presenter.getUser(userName, password);

            if (user == null) {
                System.out.println("Invalid username or password.");
            }
        } while (user == null);
        System.out.println();
        return user;
    }

	public void showMessage(String message) {
		System.out.println(message);
	}
}
