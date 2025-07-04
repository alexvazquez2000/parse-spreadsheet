package com.games.bean;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 * 
 */
@Entity
@Table(name = "seasons")
public class Season implements Serializable {

	/**  */
	private static final long serialVersionUID = -3136883359945156205L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "season_name", columnDefinition="varchar(100)")
	private String seasonName;
	
	//ie May 1st, 2025
	@Column(name = "base_date")
	private Date baseDate;
	
	/**
	 * @return the season_id
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
	 * @return the SeasonName
	 */
	public String getSeasonName() {
		return seasonName;
	}

	/**
	 * @param name the name to set
	 */
	public void setSeasonName(String name) {
		this.seasonName = name;
	}

	/**
	 * @return the base_date
	 */
	public Date getBaseDate() {
		return baseDate;
	}

	/**
	 * @param base_date the base_date to set
	 */
	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}

}
