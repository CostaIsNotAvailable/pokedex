package io.rtx.dtos.pokeapi;

import java.util.Collection;

public class PokemonSpeciesDto {
	private Integer id;
	
	private String name;
	
	private Collection<NameDto> names;
	
	private LinkDto evolution_chain;
	
	private LinkDto evolves_from_species;

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

	public Collection<NameDto> getNames() {
		return names;
	}

	public void setNames(Collection<NameDto> names) {
		this.names = names;
	}

	public LinkDto getEvolution_chain() {
		return evolution_chain;
	}

	public void setEvolution_chain(LinkDto evolution_chain) {
		this.evolution_chain = evolution_chain;
	}

	public LinkDto getEvolves_from_species() {
		return evolves_from_species;
	}

	public void setEvolves_from_species(LinkDto evolves_from_species) {
		this.evolves_from_species = evolves_from_species;
	}
}
