package io.rtx.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.rtx.entities.Pokemon;
import io.rtx.enums.Type;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
	public default Iterable<Pokemon> findAll(Type type){
		Iterable<Pokemon> pokemons = findAll();
		
		if(type != null) {
			List<Pokemon> filteredPokemons = new ArrayList<Pokemon>();
			pokemons.forEach(p -> {
				if(p.getTypes().contains(type)) {
					filteredPokemons.add(p);
				}
			});
			return filteredPokemons;
		} 
		
		return pokemons;
	}
	
	public default boolean delete(Integer id) {
		if(existsById(id)) {
			deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
