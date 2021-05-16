package io.rtx.dtos.pokeapi;

public class NameDto {
	private LanguageDto language;
	
	private String name;

	public LanguageDto getLanguage() {
		return language;
	}

	public void setLanguage(LanguageDto language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
