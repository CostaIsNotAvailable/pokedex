package io.rtx.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.rtx.enums.AttackType;

@Entity
public class Attack {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;
	
	@Enumerated(EnumType.STRING)
	private AttackType type;
	
	private Integer power;
	
	private Integer accuracy;

	private Integer cost;

	private Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private AttackType getType() {
		return type;
	}

	private void setType(AttackType type) {
		this.type = type;
	}

	private Integer getPower() {
		return power;
	}

	private void setPower(Integer power) {
		this.power = power;
	}

	private Integer getAccuracy() {
		return accuracy;
	}

	private void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	private Integer getCost() {
		return cost;
	}

	private void setCost(Integer cost) {
		this.cost = cost;
	}
}
