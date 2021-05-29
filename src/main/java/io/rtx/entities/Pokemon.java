package io.rtx.entities;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import io.rtx.enums.Type;

@Entity
public class Pokemon {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;
	
	private Integer health;
	
	@ElementCollection
	private Collection<Type> types;
	
	private String thumbnail;
	
	@OneToMany
	private Collection<Pokemon> evolutions;
	
	@OneToMany
	private Collection<Attack> attacks;
	
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

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Collection<Type> getTypes() {
		return types;
	}

	public void setTypes(Collection<Type> types) {
		this.types = types;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Collection<Pokemon> getEvolutions() {
		return evolutions;
	}

	public void setEvolutions(Collection<Pokemon> evolutions) {
		this.evolutions = evolutions;
	}

	public Collection<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(Collection<Attack> attacks) {
		this.attacks = attacks;
	}
}
