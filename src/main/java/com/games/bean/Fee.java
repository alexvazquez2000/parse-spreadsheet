package com.games.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
@Entity
@Table(name = "fees")
public class Fee implements Serializable {
	
	/** */
	private static final long serialVersionUID = 6052138255636088144L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="level_id", nullable=false)
	private GroupLevel groupLevel;

	@Column(name = "description", columnDefinition="varchar(100)")
	private String description;
	
	//General Use: DECIMAL(10,2) is suitable for most cases.
	//GAAP Compliance: DECIMAL(13,4) is recommended for greater precision in financial records.
	
	@Column(name = "amount", precision = 9, scale = 2) // Defines the DECIMAL(9,2) type in MySQL
	private BigDecimal amount; // Mapped to BigDecimal in Java

	
	public Fee() {
		//empty constructor
	}

	/**
	 * @param season
	 * @param teamName
	 */
	public Fee(GroupLevel groupLevel, String description, BigDecimal amount) {
		this.groupLevel = groupLevel;
		this.description = description;
		this.amount = amount;
	}

	
}
