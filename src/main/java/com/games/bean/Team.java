package com.games.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
@Entity
@Table(name = "teams")
@NamedQuery(name = Team.QUERY_TEAMS_BY_SEASON, query="SELECT t from Team t where t.season = :" + Team.SEASONNAME)
public class Team implements Serializable {

	private static final long serialVersionUID = 2953456146064723268L;
	
	public static final String QUERY_TEAMS_BY_SEASON = "query.team.TEAMS_BY_SEASON";
	public static final String SEASONNAME = "season";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String season;
	private String teamName;
	@ManyToMany
	private List<Player> players = new ArrayList<>();
	@ManyToMany
	private List<Coach> coaches = new ArrayList<>();
	
	public Team() {
		//empty constructor
	}

	/**
	 * @param season
	 * @param teamName
	 */
	public Team(String season, String teamName) {
		this.season = season;
		this.teamName = teamName;
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
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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

	/**
	 * @return the coaches
	 */
	public List<Coach> getCoaches() {
		return coaches;
	}

	/**
	 * @param coaches the coaches to set
	 */
	public void setCoaches(List<Coach> coaches) {
		this.coaches = coaches;
	}

	@Override
	public String toString() {
		return "Team [season=" + season + ", teamName=" + teamName
				//+ ", players=" + players
				//+ ", coaches=" + coaches
				+ "]";
	}

	
}
