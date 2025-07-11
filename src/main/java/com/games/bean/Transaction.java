package com.games.bean;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
 * 
 */
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

	/**  */
	private static final long serialVersionUID = 6286585483666006926L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "description", columnDefinition="varchar(255)")
	private String description;

	@Column(name = "reference", columnDefinition="varchar(50)")
	private String reference;

	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	@ManyToOne
	@JoinColumn(name="journal_id", nullable=false)
	private Season journalId;

	@CreationTimestamp
	@Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime createdAt;

}
