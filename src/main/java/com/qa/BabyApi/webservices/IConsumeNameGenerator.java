package com.qa.BabyApi.webservices;

import com.qa.BabyApi.persistence.domain.Name;

public interface IConsumeNameGenerator {
	
	String consumeNameGenerator(int length, String word, Name name);

}
