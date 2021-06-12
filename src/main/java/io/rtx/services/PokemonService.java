package io.rtx.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.rtx.dtos.LitePokemonDto;
import io.rtx.dtos.PokemonDto;
import io.rtx.dtos.pokeapi.ChainDto;
import io.rtx.dtos.pokeapi.EvolutionChainDto;
import io.rtx.dtos.pokeapi.MoveDto;
import io.rtx.dtos.pokeapi.PokemonSpeciesDto;
import io.rtx.entities.Attack;
import io.rtx.entities.Pokemon;
import io.rtx.enums.Type;
import io.rtx.repositories.AttackRepository;
import io.rtx.repositories.PokemonRepository;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Autowired
	private AttackRepository attackRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@Autowired
	private PokeApiService pokeApiService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Collection<LitePokemonDto> getAll(Type type){
		return toList(pokemonRepository.findAll(type));
	}
	
	public PokemonDto getById(Integer id) {
		return toDto(pokemonRepository.findById(id).get());
	}
	
	public PokemonDto put(PokemonDto pokemon) {
		return toDto(pokemonRepository.save(toEntity(pokemon)));
	}
	
	public boolean delete(Integer id) {
		return pokemonRepository.delete(id);
	}
	
	public Collection<EvolutionChainDto> populate() {
		//Attacks
		Collection<MoveDto> moves = pokeApiService.getMoves();
		Collection<Attack> attacks = new ArrayList<Attack>();
		moves.forEach(move -> attacks.add(moveDtoToAttack(move)));
		attackRepository.saveAll(attacks);
		
		//Pokemons
		Collection<io.rtx.dtos.pokeapi.PokemonDto> pokemonsDto = pokeApiService.getPokemons();
		Collection<Pokemon> pokemons = new ArrayList<Pokemon>();
		pokemonsDto.forEach(pokemon -> pokemons.add(pokemonDtoToPokemon(pokemon)));
		pokemonRepository.saveAll(pokemons);
		
		Collection<EvolutionChainDto> evolutionChains = pokeApiService.getEvolutionChains();
		System.out.println("Ici");
		evolutionChains.forEach(e -> {
			saveAssociations(e.getChain());
		});
		
		return evolutionChains;
	}
	
	//Evolution chain associations
	private void saveAssociations(ChainDto chain) {
		if(chain.getEvolves_to().isEmpty()) {
			return;
		}
		
		Integer currentPokemonId = getPokemonIdFromSpeciesUrl(chain.getSpecies().getUrl());
		System.out.println("Treating pokemon with id " + currentPokemonId);
		Pokemon currentPokemon = pokemonRepository.findById(currentPokemonId).get();
		
		Collection<Pokemon> childPokemonList = new ArrayList<Pokemon>();
		chain.getEvolves_to().forEach(c -> {
			Integer childPokemonId = getPokemonIdFromSpeciesUrl(c.getSpecies().getUrl());
			Pokemon childPokemon = pokemonRepository.findById(childPokemonId).get();
			childPokemonList.add(childPokemon);
		});
		
		currentPokemon.setEvolutions(childPokemonList);
		pokemonRepository.save(currentPokemon);
		
		chain.getEvolves_to().forEach(c -> saveAssociations(c));
	}
	
	private Integer getPokemonIdFromSpeciesUrl(String url) {
		Pattern getPokemonIdPattern = Pattern.compile("(?<=https://pokeapi.co/api/v2/pokemon-species/)(.*)(?=/)");
		Matcher m = getPokemonIdPattern.matcher(url);
		return m.find() ? Integer.parseInt(m.group(0)) : -1;
	}
	
	//Mappers
	
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
	
	private Attack moveDtoToAttack(MoveDto moveDto) {
		return modelMapper.map(moveDto, Attack.class);
	}
	
	private Pokemon pokemonDtoToPokemon(io.rtx.dtos.pokeapi.PokemonDto pokemonDto) {
		Pokemon pokemon = modelMapper.map(pokemonDto, Pokemon.class);
		
		PokemonSpeciesDto pokemonSpeciesDto = restTemplate.getForObject(pokemonDto.getSpecies().getUrl(), PokemonSpeciesDto.class);
		pokemon.setName(pokemonSpeciesDto.getNames().stream().filter(i -> i.getLanguage().getName().equals("fr")).collect(Collectors.toList()).get(0).getName());
		
		return pokemon;
	}
}
