package com.qa.BabyApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.qa.BabyApi.InputException.InputException;
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
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public Iterable<Name> getAllNames() {
		return repo.findAll();
	}

	public String deleteName(Long id) {
		repo.deleteById(id);
		return Constants.DELETE_STRING;
	}

	public Name createName(int length, String word) throws Exception {
		Name name = new Name();
		int lengthToGenerate=0;
		if(word==null) {
			lengthToGenerate=length;
		} else {
		    lengthToGenerate = length - word.length();
		}
		
		if(word==null && BusinessRules.isWordLengthAllowed(length, word)
				&& lengthToGenerate >0) {
			     name.setName(namegen.consumeNameGenerator(lengthToGenerate,word , name)); // implement other API
			     repo.save(name);
			     jmsTemplate.convertAndSend("nameQueue",name);
			     return name;
		}	
	
		else if (BusinessRules.isWordAllowed(word) && BusinessRules.isWordLengthAllowed(length, word)
				&& lengthToGenerate >= 0) {
			if(lengthToGenerate>0) {
			name.setName(word + namegen.consumeNameGenerator(lengthToGenerate, word, name)); // implement other API
			repo.save(name);
			jmsTemplate.convertAndSend("nameQueue",name);
			return name;
			} else {
				name.setName(word);
			repo.save(name);
			jmsTemplate.convertAndSend("nameQueue",name);
			return name;
			}
		} else {
			throw new InputException();
		}
	}


	public String updateName(Long id, Name name) {
		if (repo.findById(id) != null) {
			Name oldName = repo.findById(id).get();
			oldName.setName(name.getName());
			repo.save(oldName);
			return Constants.NAME_UPDATED;
		}
		return Constants.NAME_NOT_FOUND;
	}

}
