package com.qa.BabyApi.service;

import com.qa.BabyApi.persistence.domain.Name;

public interface INameService {
	
	Iterable<Name> getAllNames();
	
	String deleteName(Long id);
	
	Name createName(int length, String word) throws Exception;
	
	String updateName(Long id, Name name);

}
