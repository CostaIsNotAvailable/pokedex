package io.rtx.dtos.pokeapi;

public class NameDto {
	private LinkDto language;
	
	private String name;

	public LinkDto getLanguage() {
		return language;
	}

	public void setLanguage(LinkDto language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
