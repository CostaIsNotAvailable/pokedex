package io.rtx.repositories;

import org.springframework.data.repository.CrudRepository;

import io.rtx.entities.Pokemon;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
	public default boolean delete(Integer id) {
		if(existsById(id)) {
			deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
