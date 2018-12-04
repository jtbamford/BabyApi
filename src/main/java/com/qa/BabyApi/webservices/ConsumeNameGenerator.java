package com.qa.BabyApi.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qa.BabyApi.constants.Constants;
import com.qa.BabyApi.persistence.domain.Name;
import com.qa.BabyApi.persistence.domain.POJOName;

@RestController
@CrossOrigin
public class ConsumeNameGenerator implements IConsumeNameGenerator {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@PostMapping
	public String consumeNameGenerator(int lengthToGenerate,String word, Name name) {
		String nameGenerated=restTemplate.postForObject(Constants.HOST+Constants.PORT+Constants.NAME_GEN_LOCATION+lengthToGenerate,"",String.class);
		POJOName nameToSend = new POJOName();
		nameToSend.setId(name.getId());
		nameToSend.setName(word+nameGenerated);
		jmsTemplate.convertAndSend("nameQueue",nameToSend);
		return nameGenerated;
	}

}
