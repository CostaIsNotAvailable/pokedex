package io.rtx.repositories;

import org.springframework.data.repository.CrudRepository;

import io.rtx.entities.Pokemon;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {

}
