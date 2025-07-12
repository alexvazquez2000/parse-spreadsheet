package com.games.bean;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "levels")
public class GroupLevel implements Serializable {

	/**  */
	private static final long serialVersionUID = -1234766487330079429L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "level_name", columnDefinition="varchar(100)")
	private String levelName;
	
	@Column(name = "target_age")
	private int targetAge;

	//General Use: DECIMAL(10,2) is suitable for most cases.
	//GAAP Compliance: DECIMAL(13,4) is recommended for greater precision in financial records.
	
	// Defines the DECIMAL(9,2) type in MySQL
	// Mapped to BigDecimal in Java
	@Column(name = "registration", precision = 9, scale = 2)
	private BigDecimal registration;

	@Column(name = "team_fee", precision = 9, scale = 2)
	private BigDecimal teamFee;

	@Column(name = "uniform", precision = 9, scale = 2)
	private BigDecimal uniform;

	/**
	 * @param levelName
	 * @param targetAge
	 * @param registration
	 * @param teamFee
	 * @param uniform
	 */
	public GroupLevel(String levelName, int targetAge,
			BigDecimal registration, BigDecimal teamFee, BigDecimal uniform) {
		this.levelName = levelName;
		this.targetAge = targetAge;
		this.registration = registration;
		this.teamFee = teamFee;
		this.uniform = uniform;
	}

	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * @return the targetAge
	 */
	public int getTargetAge() {
		return targetAge;
	}

	/**
	 * @param targetAge the targetAge to set
	 */
	public void setTargetAge(int targetAge) {
		this.targetAge = targetAge;
	}

	/**
	 * @return the registration
	 */
	public BigDecimal getRegistration() {
		return registration;
	}

	/**
	 * @param registration the registration to set
	 */
	public void setRegistration(BigDecimal registration) {
		this.registration = registration;
	}

	/**
	 * @return the teamFee
	 */
	public BigDecimal getTeamFee() {
		return teamFee;
	}

	/**
	 * @param teamFee the teamFee to set
	 */
	public void setTeamFee(BigDecimal teamFee) {
		this.teamFee = teamFee;
	}

	/**
	 * @return the uniform
	 */
	public BigDecimal getUniform() {
		return uniform;
	}

	/**
	 * @param uniform the uniform to set
	 */
	public void setUniform(BigDecimal uniform) {
		this.uniform = uniform;
	}



}
