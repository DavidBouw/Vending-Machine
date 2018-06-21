package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.techelevator.Item;

public class Menu {

	private Scanner in;
	private String[] mainMenu = new String[] {"(1) Display Vending Machine Items", "(2) Purchase"};
	private String[] PurchaseMenu = new String[] {"(1) Feed Money", "(2) Select Product", "(3) Finish Transaction"};

	public Menu(InputStream input) {
		this.in = new Scanner(input);
	}
	
	public int promptUserForSelection(String[] currentMenu, String prompt) {
		String userInput = "";
		System.out.println(prompt);
		while (((userInput = in.nextLine()) == "")
				|| (Integer.parseInt(userInput) > currentMenu.length)
				|| (Integer.parseInt(userInput) < 1)) {
			System.out.print(prompt);
		}
		
		return Integer.parseInt(userInput);
	}

	public Object getChoiceFromOptions(Item[] options) {
		Object choice = null;
		while(choice == null) {
			//displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			//out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}
	
	public String[] getMainMenu() {
		return this.mainMenu;
	}
	
	public String[] getPurchaseMenu() {
		return this.PurchaseMenu;
	}

	public void displayMenuOptions(String[] options) {
		for(String option : options) {
			System.out.println(option);
		}
	}
}
