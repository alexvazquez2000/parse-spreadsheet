package com.games.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
@Entity
@Table(name = "parents")
public class Parent implements Serializable {

	private static final long serialVersionUID = -7659092553592436364L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "first_name", columnDefinition="varchar(100)")
	private String firstName;
	@Column(name = "last_name", columnDefinition="varchar(100)")
	private String lastName;
	@Column(columnDefinition="varchar(20)")
	private String phone;
	@Column(columnDefinition="varchar(100)")
	private String email;
	@ManyToMany(mappedBy= "parents")
	private List<Player> players = new ArrayList<>();


	public Parent() {
		//empty constructor
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param email
	 */
	public Parent(String firstName, String lastName, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Parent [Name=" + firstName + " " + lastName + ", phone=" + phone + ", email=" + email
				//+ ", players=" + players
				+ "]";
	}

	
}
