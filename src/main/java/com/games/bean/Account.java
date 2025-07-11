package com.games.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 * 
 */
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

	public enum AccountType {
		asset, liability, equity, revenue, expense;
	}

	/**  */
	private static final long serialVersionUID = -4479732256472334466L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", columnDefinition="varchar(100)", nullable = false)
	private String name;

	@Column(name = "code", columnDefinition="varchar(20)", nullable = false)
	private String code;

	// Explicitly specifies ordinal mapping
	@Enumerated(EnumType.STRING)
	@Column(name = "account_type", nullable = false)
	private AccountType accountType;

	//DATETIME(6): This specifically means the DATETIME value will store and display
	//microseconds precision, which is 6 digits after the decimal point.
	//For example, 1970-01-01 17:51:04.789463
	
	@CreationTimestamp
	@Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public Account(String name, String code, AccountType accountType) {
		this.name = name;
		this.code = code;
		this.accountType = accountType;
	}


}
