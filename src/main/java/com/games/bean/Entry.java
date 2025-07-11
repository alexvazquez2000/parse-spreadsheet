package com.games.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.games.bean.Account.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 * 
 */
@Entity
@Table(name = "entries")
public class Entry implements Serializable {

	public enum EntryType {
		debit, credit;
	}

	/**  */
	private static final long serialVersionUID = 5202839761616298359L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name="transaction_id", nullable=false)
	private Season transactionId;

	@ManyToOne
	@JoinColumn(name="account_id", nullable=false)
	private Season accountId;

	// Defines the DECIMAL(9,2) type in MySQL
	// Mapped to BigDecimal in Java
	@Column(name = "amount", precision = 9, scale = 2)
	private BigDecimal amount;

	// Explicitly specifies ordinal mapping
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "entry_type", nullable = false)
	private EntryType entryType;

	@Column(name = "memo", columnDefinition="varchar(255)")
	private String memo;

	@CreationTimestamp
	@Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	//TODO: backref transaction
	//TODO: backref account

}
