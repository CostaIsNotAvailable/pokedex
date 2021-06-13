package io.rtx.dtos.pokeapi;

import java.util.Collection;

public class PokemonDto {
	private Integer id;
	
	private Collection<MovesListItemDto> moves;
	
	private String name;
	
	private LinkDto species;
	
	private Collection<StatDto> stats;
	
	private Collection<TypeDto> types;
	
	private SpriteDto sprites;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<MovesListItemDto> getMoves() {
		return moves;
	}

	public void setMoves(Collection<MovesListItemDto> moves) {
		this.moves = moves;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkDto getSpecies() {
		return species;
	}

	public void setSpecies(LinkDto species) {
		this.species = species;
	}

	public Collection<StatDto> getStats() {
		return stats;
	}

	public void setStats(Collection<StatDto> stats) {
		this.stats = stats;
	}

	public Collection<TypeDto> getTypes() {
		return types;
	}

	public void setTypes(Collection<TypeDto> types) {
		this.types = types;
	}

	public SpriteDto getSprites() {
		return sprites;
	}

	public void setSprites(SpriteDto sprites) {
		this.sprites = sprites;
	}
}
