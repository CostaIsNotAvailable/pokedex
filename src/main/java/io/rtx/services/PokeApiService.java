package io.rtx.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.rtx.dtos.pokeapi.EvolutionChainDto;
import io.rtx.dtos.pokeapi.LinkDto;
import io.rtx.dtos.pokeapi.MoveDto;
import io.rtx.dtos.pokeapi.PokemonSpeciesDto;
import io.rtx.dtos.pokeapi.PokemonDto;
import io.rtx.dtos.pokeapi.ListDto;
import io.rtx.entities.Attack;
import io.rtx.entities.Pokemon;
import io.rtx.enums.Type;

@Service
public class PokeApiService {
	public static final String movesListUrl = "https://pokeapi.co/api/v2/move?limit=-1";
	public static final String pokemonsListUrl = "https://pokeapi.co/api/v2/pokemon?limit=-1";
	public static final String evolutionChainsListUrl = "https://pokeapi.co/api/v2/evolution-chain?limit=10";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Collection<MoveDto> getMoves(){
		ListDto movesList = restTemplate.getForObject(movesListUrl, ListDto.class);
		Collection<LinkDto> movesUrls = movesList.getResults();
		
		Collection<MoveDto> moves = new ArrayList<MoveDto>();
		movesUrls.forEach(m -> {
			MoveDto move = restTemplate.getForObject(m.getUrl(), MoveDto.class);
			moves.add(move);
		});
		return moves;
	}
	
	public Collection<PokemonDto> getPokemons(){
		ListDto pokemonsList = restTemplate.getForObject(pokemonsListUrl,ListDto.class);
		Collection<LinkDto> pokemonsUrls = pokemonsList.getResults();
		
		Collection<PokemonDto> pokemons = new ArrayList<PokemonDto>();
		pokemonsUrls.forEach(p -> {
			PokemonDto pokemonDto = restTemplate.getForObject(p.getUrl(), PokemonDto.class);
			pokemons.add(pokemonDto);
		});
		return pokemons;
	}
	
	public Collection<EvolutionChainDto> getEvolutionChains(){
		ListDto evolutionChainsList = restTemplate.getForObject(evolutionChainsListUrl, ListDto.class);
		Collection<LinkDto> evolutionChainsUrls = evolutionChainsList.getResults();
		
		Collection<EvolutionChainDto> evolutionChains = new ArrayList<EvolutionChainDto>();
		evolutionChainsUrls.forEach(e -> {
			EvolutionChainDto evolutionChainDto = restTemplate.getForObject(e.getUrl(), EvolutionChainDto.class);
			evolutionChains.add(evolutionChainDto);
		});
		
		return evolutionChains;
	}
}
