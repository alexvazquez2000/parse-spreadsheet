package com.games.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "journals")
public class Journal implements Serializable {

	/**  */
	private static final long serialVersionUID = 6286585483666006926L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", columnDefinition="varchar(100)", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition="TEXT")
	private String description;

	@ColumnDefault("true")
	@Column(name = "allow_manual_entries", nullable = false)
	private boolean allowManualEntries = true; //initialize on java too
	
	//DATETIME(6): This specifically means the DATETIME value will store and display
	//microseconds precision, which is 6 digits after the decimal point.
	//For example, 1970-01-01 17:51:04.789463
	
	@CreationTimestamp
	@Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime createdAt;


}
