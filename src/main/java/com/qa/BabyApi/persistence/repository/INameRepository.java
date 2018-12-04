package com.qa.BabyApi.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qa.BabyApi.persistence.domain.Name;

@Repository
public interface INameRepository extends CrudRepository<Name,Long> {

}
