package io.rtx.dtos;

import java.util.Collection;

import io.rtx.enums.AttackType;

public class PokemonDto {
	private Integer id;
	
	private String name;
	
	private Integer health;
	
	private Collection<AttackType> types;
	
	private String thumbnail;
	
	private Collection<PokemonDto> evolutions;
	
	private Collection<AttackDto> attacks;
	
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

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Collection<AttackType> getTypes() {
		return types;
	}

	public void setTypes(Collection<AttackType> types) {
		this.types = types;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Collection<PokemonDto> getEvolutions() {
		return evolutions;
	}

	public void setEvolutions(Collection<PokemonDto> evolutions) {
		this.evolutions = evolutions;
	}

	public Collection<AttackDto> getAttacks() {
		return attacks;
	}

	public void setAttacks(Collection<AttackDto> attacks) {
		this.attacks = attacks;
	}
}
