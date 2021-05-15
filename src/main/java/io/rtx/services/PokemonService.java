package io.rtx.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rtx.dtos.LitePokemonDto;
import io.rtx.dtos.PokemonDto;
import io.rtx.entities.Pokemon;
import io.rtx.repositories.PokemonRepository;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	public Collection<LitePokemonDto> getAll(){
		return toList(pokemonRepository.findAll());
	}
	
	private PokemonDto toDto(Pokemon pokemon) {
		return modelMapper.map(pokemon, PokemonDto.class);
	}
	
	private Pokemon toEntity(PokemonDto pokemonDto) {
		return modelMapper.map(pokemonDto, Pokemon.class);
	}
	
	private LitePokemonDto toLiteDto(Pokemon pokemon) {
		return modelMapper.map(pokemon, LitePokemonDto.class);
	}
	
	private List<LitePokemonDto> toList(Iterable<Pokemon> pokemons){
		List<LitePokemonDto> pokemonsDtos = new ArrayList<LitePokemonDto>();
		pokemons.forEach(p -> pokemonsDtos.add(toLiteDto(p)));
		return pokemonsDtos;
	}
}
