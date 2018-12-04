package com.qa.BabyApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.BabyApi.constants.Constants;
import com.qa.BabyApi.persistence.domain.Name;
import com.qa.BabyApi.persistence.repository.INameRepository;
import com.qa.BabyApi.webservices.IConsumeNameGenerator;

@Service
public class NameService implements INameService {
	
	@Autowired
	private INameRepository repo;
	
	@Autowired
	private IConsumeNameGenerator namegen;
	
	
	public Iterable<Name> getAllNames() {
		return repo.findAll();
	}
	
	public String deleteAccount(Long id) {
		repo.deleteById(id);
		return Constants.DELETE_STRING;
	}

	public Name createName(Name name) {
		name.setName(namegen.consumeNameGenerator(name)); //implement other API
		return repo.save(name);
	}
	
	public String updateAccount(Long id, Name name) {
		if (repo.findById(id) != null) {
			Name oldName = repo.findById(id).get();
			oldName.setName(name.getName());
			return Constants.NAME_UPDATED;
		}
		return Constants.NAME_NOT_FOUND;
	}

}
