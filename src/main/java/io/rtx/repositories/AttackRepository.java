package io.rtx.repositories;

import org.springframework.data.repository.CrudRepository;

import io.rtx.entities.Attack;

public interface AttackRepository extends CrudRepository<Attack, Integer> {
}
