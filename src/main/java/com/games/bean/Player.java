package com.games.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
@Entity
@Table(name = "players")
public class Player implements Serializable {

	private static final long serialVersionUID = -1998819810787384840L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Parent> parents = new ArrayList<>();
	private Parent altParent;
	private String dob;
	private Date date_of_birth;
	private int jersey_number;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "players")
	private List<Team> teams = new ArrayList<>();

	public Player() {
		//empty constructor
	}

	/**
	 * @param name
	 * @param dob
	 * @param jersey_number
	 */
	public Player(String name, String dob, int jersey_number) {
		this.name = name;
		this.dob = dob;
		this.jersey_number = jersey_number;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parents
	 */
	public List<Parent> getParents() {
		return parents;
	}

	/**
	 * @param parents the parents to set
	 */
	public void setParents(List<Parent> parents) {
		this.parents = parents;
	}

	/**
	 * @return the altParent
	 */
	public Parent getAltParent() {
		return altParent;
	}

	/**
	 * @param altParent the altParent to set
	 */
	public void setAltParent(Parent altParent) {
		this.altParent = altParent;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the date_of_birth
	 */
	public Date getDate_of_birth() {
		return date_of_birth;
	}

	/**
	 * @param date_of_birth the date_of_birth to set
	 */
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	/**
	 * @return the jersey_number
	 */
	public int getJersey_number() {
		return jersey_number;
	}

	/**
	 * @param jersey_number the jersey_number to set
	 */
	public void setJersey_number(int jersey_number) {
		this.jersey_number = jersey_number;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}


	@Override
	public String toString() {
		return "Player [name=" + name + ", dob=" + dob
				+ ", jersey_number=" + jersey_number + "]";
	}


}
