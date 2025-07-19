package com.games.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
	
	@ManyToMany(mappedBy= "parents")
	private List<Player> players = new ArrayList<>();

	//@OneToOne(mappedBy = "parent")
	//private User user;


	public Parent() {
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

}
