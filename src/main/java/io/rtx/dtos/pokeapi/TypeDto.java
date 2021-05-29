package io.rtx.dtos.pokeapi;

public class TypeDto {
	private Integer slot;
	
	private LinkDto type;

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public LinkDto getType() {
		return type;
	}

	public void setType(LinkDto type) {
		this.type = type;
	}
}
