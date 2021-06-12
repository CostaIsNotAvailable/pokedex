package io.rtx.dtos.pokeapi;

public class EvolutionChainDto {
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChainDto getChain() {
		return chain;
	}

	public void setChain(ChainDto chain) {
		this.chain = chain;
	}

	private ChainDto chain;
}
