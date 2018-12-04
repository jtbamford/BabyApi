package com.qa.BabyApi.persistence.domain;

import java.io.Serializable;

public class POJOName implements Serializable {

	private Long id;
	private String name;
	
	public POJOName() {
		
	}
	
	public POJOName(String name) {
		this.name=name;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
