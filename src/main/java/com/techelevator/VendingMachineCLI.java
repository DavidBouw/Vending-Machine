package com.techelevator;

import com.techelevator.view.Menu;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE };
	
	
	
	public VendingMachineCLI() {
		
	}
	/*
	public void run() {
		while(true) {
			//String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			}
		}
	}*/
	
	public static class VendingMachine{
		public static Item[] inventory;
		public static Menu menu = new Menu(System.in);
		private static double totalMoneyInMachine = 0.0;
		
		public static Item[] restock(File restockInfoFile) {
			String itemInfoUnsplit;
			List<Item> tempInventoryList = new ArrayList<Item>();
			try (BufferedReader fReader = new BufferedReader(new FileReader(restockInfoFile))) {
				
				
				
				while ((itemInfoUnsplit = fReader.readLine()) != null) {
					tempInventoryList.add(new Item(itemInfoUnsplit.split("[|]")));
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("Could not load restock file.");
			} catch (IOException e) {
				System.out.println("Could not read file.");
			}
			
			return  tempInventoryList.toArray(new Item[tempInventoryList.size()]);
		}
		
		public static Item findItem(String id) {
			for(Item entry : inventory) {
				if(id.equals(entry.getID())) {
					return entry;
				}
			}
			
			return null;
		}
	}
	
	public static void main(String[] args) {
		File restockFile;
		int userSelection;
		String idToBuy;
		
		if (args.length > 0) {
			restockFile = new File(args[0]);
		}else {
			restockFile = new File("vendingmachine.csv");
		}
		VendingMachine.inventory = VendingMachine.restock(restockFile);
		
		//for debug purposes while building menus
		while(true) {
			VendingMachine.menu.displayMenuOptions(VendingMachine.menu
					.getMainMenu());
			userSelection = VendingMachine.menu
					.promptUserForSelection(VendingMachine.menu
							.getMainMenu(), "Your selection: ");
			
			if(userSelection == 1) {
				for(Item element : VendingMachine.inventory) {
					System.out.println(element.getID() + "\t\t" 
							+ element.getName() + "\t\t" + element.getMessage() 
							+ "\t\t" + element.getPrice() + "\t\t" + element.getStock());
				}
			} else if (userSelection == 2) {
				VendingMachine.menu.displayMenuOptions(VendingMachine
						.menu.getPurchaseMenu());
				System.out.println("Current Money Provided: $" + VendingMachine.totalMoneyInMachine);;
				userSelection = VendingMachine.menu
						.promptUserForSelection(VendingMachine.menu
								.getPurchaseMenu(), "Your selection: ");
				switch(userSelection) {
					case 1:
						VendingMachine.totalMoneyInMachine += VendingMachine.menu.promptUserForMoney(
								VendingMachine.totalMoneyInMachine);
						break;
					case 2:
						idToBuy = VendingMachine.menu.promptUserForItemID(
								VendingMachine.inventory
								, VendingMachine.totalMoneyInMachine);
						System.out.println(VendingMachine.findItem(idToBuy).getMessage());
						break;
				}
			}
		}
		
	}
}
