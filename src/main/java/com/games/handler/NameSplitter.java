package com.games.handler;

public class NameSplitter {

	private String name;
	private String firstName;
	private String lastName;
	
	public NameSplitter(String name) {
		this.name = name.trim();
		firstName = this.name.substring(0, this.name.lastIndexOf(" "));
		lastName = this.name.substring(this.name.lastIndexOf(" ") + 1);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	
}
