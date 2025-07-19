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
import jakarta.persistence.OneToOne;
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

	//@OneToOne(mappedBy = "coach")
	//private User user;
	
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

}
