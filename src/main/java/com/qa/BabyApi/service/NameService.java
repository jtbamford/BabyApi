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
	
	public String deleteName(Long id) {
		repo.deleteById(id);
		return Constants.DELETE_STRING;
	}

	public Name createName(int length, String word) throws Exception {
		Name name = new Name();
		int lengthToGenerate=length-word.length();
		if(lengthToGenerate<0) {
			throw new Exception();
		} else {
			if(BusinessRules.isWordAllowed(word) && BusinessRules.isWordLengthAllowed(length,word)) {
		name.setName(word+namegen.consumeNameGenerator(lengthToGenerate,word,name)); //implement other API
		return repo.save(name);
			} else {
				throw new Exception();
			}
		}
	}
	
	public String updateName(Long id, Name name) {
		if (repo.findById(id) != null) {
			Name oldName = repo.findById(id).get();
			oldName.setName(name.getName());
			return Constants.NAME_UPDATED;
		}
		return Constants.NAME_NOT_FOUND;
	}

}
