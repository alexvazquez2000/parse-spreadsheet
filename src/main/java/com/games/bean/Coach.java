package com.games.bean;

import java.io.Serializable;
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
 * 
 */
@Entity
@Table(name = "coaches")
public class Coach implements Serializable {

	private static final long serialVersionUID = -6623325592656220276L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String phone;
	private String email;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "coaches")
	private List<Team> teams;

	/**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Coach() {
		//empty constructor
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param email
	 */
	public Coach(String coachName, String phone, String email) {
		this.name = coachName;
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
	 * @return the coachName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param coachName name the coachName to set
	 */
	public void setName(String coachName) {
		this.name = coachName;
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

	@Override
	public String toString() {
		return "Coach [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
	}
}
