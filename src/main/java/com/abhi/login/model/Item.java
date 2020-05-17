package com.abhi.login.model;

public class Item {
	public int id;
	public String itemName;
	public User owner;

	public Item() {
	}

	public Item(int id, String itemName, User owner) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
