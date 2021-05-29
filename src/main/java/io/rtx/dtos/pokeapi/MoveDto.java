package io.rtx.dtos.pokeapi;

import java.util.Collection;

public class MoveDto {
	private Integer id;
	
	private Integer accuracy;
	
	private Collection<NameDto> names;
	
	private Integer power;
	
	private LinkDto type;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	public Collection<NameDto> getNames() {
		return names;
	}

	public void setNames(Collection<NameDto> names) {
		this.names = names;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public LinkDto getType() {
		return type;
	}

	public void setType(LinkDto type) {
		this.type = type;
	}
}
