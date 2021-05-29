package io.rtx.dtos.pokeapi;

public class StatDto {
	private Integer base_stat;
	
	private Integer effort;
	
	private LinkDto stat;

	public Integer getBase_stat() {
		return base_stat;
	}

	public void setBase_stat(Integer base_stat) {
		this.base_stat = base_stat;
	}

	public Integer getEffort() {
		return effort;
	}

	public void setEffort(Integer effort) {
		this.effort = effort;
	}

	public LinkDto getStat() {
		return stat;
	}

	public void setStat(LinkDto stat) {
		this.stat = stat;
	}
}
