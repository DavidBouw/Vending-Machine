package com.techelevator.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

import com.techelevator.Item;

public class Menu {

	private Scanner in;
	private String[] mainMenu = new String[] {"(1) Display Vending Machine Items", "(2) Purchase"};
	private String[] PurchaseMenu = new String[] {"(1) Feed Money", "(2) Select Product", "(3) Finish Transaction"};
	
	
	public Menu(InputStream input) {
		this.in = new Scanner(input);
	}
	
	//
	
	/**
	 * This method is passed the menu String[] of whichever screen the 
	 * user is navigating to (MainMenu is default)
	 * The prompt String is the message that will be displayed
	 * for the user.
	 * @param currentMenu
	 * @param prompt
	 * @return int the numerical option the user has selected.
	 */
	public int promptUserForSelection(String[] currentMenu, String prompt) {
		String userInput = "";
		int selection = 0;
		
		
		while ((selection == 4)
				|| (selection > currentMenu.length)
				|| (selection < 1)) {
			System.out.print(prompt);
			userInput = in.nextLine();
			try {
				selection = Integer.parseInt(userInput);
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection.");
			}
		}
		
		return selection;
	}
	
	/**
	 * This method is passed the menu String[] of whichever screen the 
	 * user is navigating to (MainMenu is default)
	 * The prompt String is the message that will be displayed
	 * for the user.
	 * @param double currentTotal the amount of money that is already in the machine
	 * @return double total amount of money in the machine
	 */
	public double promptUserForMoney(double currentTotal) {
		double dollarValue = 0.0;
		do {
			String userInput = "0";
			try {
				dollarValue = Double.parseDouble(userInput);
			} catch (NumberFormatException e) {
				System.out.println("Invalid money amount.");
			}
			
			while ((dollarValue != 1)
					&& (dollarValue != 2) 
					&& (dollarValue != 5)
					&& (dollarValue!= 10)) {
				
				System.out.print("Insert a US Bill (Sorry. No $20s allowed.): ");
				userInput = in.nextLine();
				dollarValue = Double.parseDouble(userInput);
			}
			
			currentTotal += dollarValue;
			
			System.out.println("Current Money Provided: $" + currentTotal);
		} while(promptUserYesOrNo("Do you want to put more money into the machine? [y/n]"));
		
		return currentTotal;
	}
	
	public String promptUserForItemID(Item[] inventory, double currentTotal) {
		String currentSelection = "";
		System.out.print("Please enter the code for the item you would like to buy: ");
		currentSelection = in.nextLine();
		for(Item entry : inventory) {
			if(currentSelection.equals(entry.getID())) {
				if(entry.getStock() == 0) {
					System.out.println("That item is out of stock. Sorry.");
					break;
				} else if(currentTotal < entry.getPrice()) {
					System.out.println("Insufficient funds provided.");
					break;
				}
				
				return entry.getID();
			}
		}
		
		return "";
	}
	
	private boolean promptUserYesOrNo(String message) {
		String choice = "";
		
		do {
			System.out.print(message);
			choice = in.nextLine();
			
			
		} while(!choice.equalsIgnoreCase("n") 
				&& !choice.equalsIgnoreCase("y"));
		
		if (choice.equalsIgnoreCase("n")) {
			return false;
		} else {
			return true;
		}
	}
/*
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
	*/
	
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
