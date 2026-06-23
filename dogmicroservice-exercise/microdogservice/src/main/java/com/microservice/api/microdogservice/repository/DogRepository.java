package com.microservice.api.microdogservice.repository;

import  com.microservice.api.microdogservice.entity.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {

}
