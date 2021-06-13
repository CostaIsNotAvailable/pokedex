package io.rtx.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.rtx.dtos.LitePokemonDto;
import io.rtx.dtos.PokemonDto;
import io.rtx.dtos.pokeapi.EvolutionChainDto;
import io.rtx.enums.Type;
import io.rtx.services.PokemonService;

@Controller
@RequestMapping(path="/pokemon")
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;
	
	@GetMapping
	public @ResponseBody Collection<LitePokemonDto> getAll(@RequestParam(required=false) Type type){
		return pokemonService.getAll(type);
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody PokemonDto getById(@PathVariable Integer id) {
		return pokemonService.getById(id);
	}
	
	@PutMapping(path="/{id}")
	public @ResponseBody PokemonDto put(@RequestBody PokemonDto pokemon) {
		return pokemonService.put(pokemon);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<PokemonDto> delete(@PathVariable Integer id) {
		return pokemonService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path="/populate")
	public ResponseEntity<PokemonDto> populate(){
		return pokemonService.populate() ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
