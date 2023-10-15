package com.devcaotics.doapet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devcaotics.doapet.entities.Animal;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long>
{}
