package io.rtx.dtos.pokeapi;

import java.util.Collection;

public class ListDto {
	private Integer count;
	
	private String next;
	
	private String previous;
	
	private Collection<LinkDto> results;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public Collection<LinkDto> getResults() {
		return results;
	}

	public void setResults(Collection<LinkDto> results) {
		this.results = results;
	}
}
