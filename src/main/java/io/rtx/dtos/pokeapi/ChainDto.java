package io.rtx.dtos.pokeapi;

import java.util.Collection;

public class ChainDto {
	private Collection<ChainDto> evolves_to;
	
	private LinkDto species;

	public Collection<ChainDto> getEvolves_to() {
		return evolves_to;
	}

	public void setEvolves_to(Collection<ChainDto> evolves_to) {
		this.evolves_to = evolves_to;
	}

	public LinkDto getSpecies() {
		return species;
	}

	public void setSpecies(LinkDto species) {
		this.species = species;
	}
}
