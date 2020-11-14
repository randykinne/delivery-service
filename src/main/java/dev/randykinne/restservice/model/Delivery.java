package dev.randykinne.restservice.model;


public class Delivery {

	private int id;
	private final String name;
	private final String address;

	public Delivery(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() { return this.address; }
}
