package com.games.bean;

import java.io.Serializable;

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

	public GroupLevel(String levelName, int targetAge) {
		this.levelName = levelName;
		this.targetAge = targetAge;
	}

}
