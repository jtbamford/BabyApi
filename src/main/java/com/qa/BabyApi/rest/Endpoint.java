package com.qa.BabyApi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.BabyApi.persistence.domain.Name;
import com.qa.BabyApi.service.INameService;

@RequestMapping("{base_endpoint}")
@RestController
@CrossOrigin
public class Endpoint {
	
	@Autowired
	private INameService service;
	
	@GetMapping("${getall_endpoint}")
	public Iterable<Name> getAllNames() {
		return service.getAllNames();
	}
	
	@PostMapping("${create_endpoint}")
	public Name createName(@PathVariable int length ,@RequestBody String word) throws Exception {
		return service.createName(length,word);
	}
	
	@DeleteMapping("${delete_endpoint}")
	public String deleteAccount(@PathVariable Long id) {
		return service.deleteName(id);
	}
	
	@PutMapping("${update_endpoint}")
	public String updateAccount(@PathVariable Long id, @RequestBody Name name) {
		return service.updateName(id, name);
	}

}
