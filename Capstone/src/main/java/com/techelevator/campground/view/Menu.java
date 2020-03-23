package com.techelevator.campground.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.Site;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	
	public Object getChoiceFromParkOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayParkMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	public Object getChoiceFromSiteOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displaySiteMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}


	private void displayMenuOptions(Object[] options) {
		for (int i = 0; i <= options.length; i++) {
			int optionNum = i + 1;
			if (i == options.length) {
				System.out.println("Q) quit \n");
			} else {
				System.out.println(optionNum + ") " + options[i]);
			}
		}
		System.out.print("\nPlease choose an option >>> ");
	}

	private void displayParkMenuOptions(Object[] options) {
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			System.out.println(optionNum + ") " + options[i]);
		}
		System.out.print("\nPlease choose an option >>> ");
		
	}
	private void displaySiteMenuOptions(Object[] options) {
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			System.out.println(optionNum + ") " + options[i]);
		}
		System.out.print("\nWhich site should be reserved (press 0 to cancel)? ");
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		if (userInput.equalsIgnoreCase("Q"))
			System.exit(0);
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will
			// be null
		}
		if (choice == null) {
			System.out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}
	public LocalDate getArrivalDate() {
		System.out.println("What is the arrival date?\n");
		String userSelectionString = in.nextLine();
		String[] userSelectedDateStrings = userSelectionString.split("/");
		int month = Integer.parseInt(userSelectedDateStrings[0]);
		int day = Integer.parseInt(userSelectedDateStrings[1]);
		int year = Integer.parseInt(userSelectedDateStrings[2]);
		LocalDate date = LocalDate.of(year, month, day);
		return date;
	}
	public LocalDate getDepartureDate() {
		System.out.print("What is the departure date?\n");
		String userSelectionString = in.nextLine();
		String[] userSelectedDateStrings = userSelectionString.split("/");
		int month = Integer.parseInt(userSelectedDateStrings[0]);
		int day = Integer.parseInt(userSelectedDateStrings[1]);
		int year = Integer.parseInt(userSelectedDateStrings[2]);
		LocalDate date = LocalDate.of(year, month, day);
		return date;
	}
	public String getReservationName() {
		System.out.print("What name should the reservation be made under?");
		return in.nextLine().toString();
	}
	public void printSiteInfo(Site site, String reservationName) {
		System.out.format("Great! You've selected Site No. %s and your reservation is saved under name: %s \n",
								site.getId(), reservationName);
	}
}
