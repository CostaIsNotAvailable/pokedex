package io.rtx.dtos;

import io.rtx.enums.AttackType;

public class AttackDto {
	private Integer id;

	private String name;
	
	private AttackType type;
	
	private Integer power;
	
	private Integer accuracy;

	private Integer cost;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttackType getType() {
		return type;
	}

	public void setType(AttackType type) {
		this.type = type;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
}
