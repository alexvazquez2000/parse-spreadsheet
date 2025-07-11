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
@Table(name = "audit_logs")
public class AuditLog implements Serializable {

	/**  */
	private static final long serialVersionUID = -5406814420353358358L;

	public enum Action {
		insert, update, delete;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "table_name", columnDefinition="varchar(100)", nullable = false)
	private String tableName;

	@Column(name = "record_id", nullable = false)
	private int recordId;

	// Explicitly specifies ordinal mapping
	@Enumerated(EnumType.STRING)
	@Column(name = "action", nullable = false)
	private Action action;

	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "change_summary", columnDefinition="TEXT")
	private String changeSummary;

	@CreationTimestamp
	@Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
	private LocalDateTime createdAt;

}
