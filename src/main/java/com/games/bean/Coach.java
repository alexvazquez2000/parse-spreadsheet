package com.games.bean;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	@Column(name = "first_name", columnDefinition="varchar(100)")
	private String firstName;
	@Column(name = "last_name", columnDefinition="varchar(100)")
	private String lastName;
	@Column(columnDefinition="varchar(20)")
	private String phone;
	@Column(columnDefinition="varchar(100)")
	private String email;
	
	//BLOB: Can handle up to 65,535 bytes of data.
	//MEDIUMBLOB: The maximum length supported is 16,777,215 bytes.
	//LONGBLOB: Stores up to 4,294,967,295 bytes of data.
	//photos from my cell are in the 60KB to 555KB size

	@Lob // Mark this property as a Large Object
	@Column(name = "photo", columnDefinition = "MEDIUMBLOB") // Optional column details
	private byte[] photo;
	
	@Lob // Mark this property as a Large Object
	@Column(name = "thumbnail", columnDefinition = "BLOB") // Optional column details
	private byte[] thumbnail;
	
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
	public Coach(String firstName, String lastName, String phone, String email) {
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

	@Override
	public String toString() {
		return "Coach [id=" + id + ", name=" + firstName + " " + lastName + ", phone=" + phone + ", email=" + email + "]";
	}
}
