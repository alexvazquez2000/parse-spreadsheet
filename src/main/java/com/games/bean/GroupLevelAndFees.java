package com.games.bean;

import java.math.BigDecimal;

public class GroupLevelAndFees {
	public String levelName;
	public int targetAge;
	public BigDecimal registration;
	public BigDecimal teamFee;
	public BigDecimal uniform;

	public GroupLevelAndFees(String levelName, int targetAge, BigDecimal registration, BigDecimal teamFee,
			BigDecimal uniform) {
		super();
		this.levelName = levelName;
		this.targetAge = targetAge;
		this.registration = registration;
		this.teamFee = teamFee;
		this.uniform = uniform;
	}
	
}
