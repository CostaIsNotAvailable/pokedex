package io.rtx.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.rtx.dtos.pokeapi.ListItemDto;
import io.rtx.dtos.pokeapi.MoveDto;
import io.rtx.dtos.pokeapi.ListDto;
import io.rtx.entities.Attack;
import io.rtx.enums.AttackType;

@Service
public class PokeApiService {
	public static final String movesListUrl = "https://pokeapi.co/api/v2/move?limit=-1";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Collection<Attack> getAttacks(){
		ListDto movesList = restTemplate.getForObject(movesListUrl, ListDto.class);
		Collection<ListItemDto> moves = movesList.getResults();
		
		Collection<Attack> attacks = new ArrayList<Attack>();
		moves.forEach(m -> {
			MoveDto move = restTemplate.getForObject(m.getUrl(), MoveDto.class);
			attacks.add(moveDtoToAttack(move));
		});
		return attacks;
	}
	
	private Attack moveDtoToAttack(MoveDto moveDto) {
		Attack attack = new Attack();
		attack.setId(moveDto.getId());
		attack.setName(moveDto.getNames().stream().filter(i -> i.getLanguage().getName().equals("fr")).collect(Collectors.toList()).get(0).getName());
		attack.setType(AttackType.valueOf(moveDto.getType().getName().toUpperCase()));
		attack.setPower(moveDto.getPower());
		attack.setAccuracy(moveDto.getAccuracy());
		attack.setCost(0);
		return attack;
	}
}
