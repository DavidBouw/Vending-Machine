package com.techelevator;

public class Item {
	
	private String name, message, ID;
	private double price;
	private int stock;
	
	public Item(String[] restockingInfo) {
		this.stock = 5;
		this.ID = restockingInfo[0];
		this.name = restockingInfo[1];
		this.price = Double.parseDouble(restockingInfo[2]);
		this.message = createMessage(restockingInfo[3]);
	}
	
	private String createMessage(String type) {
		if (type.equals("Chip")) {
			return "Crunch Crunch, Yum!";
		} else if (type.equals("Candy")) {
			return "Munch Munch, Yum!";
		} else if (type.equals("Drink")) {
			return "Glug Glug, Yum!";
		} else if (type.equals("Gum")) {
			return "Chew Chew, Yum!";
		} else {
			return "Invalid Type";
		}
 	}
	
	public String getName() {
		return this.name;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public void setStock() {
		this.stock--;
	}

}
