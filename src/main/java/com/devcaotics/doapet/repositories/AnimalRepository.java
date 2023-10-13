package com.devcaotics.doapet.repositories;

import org.springframework.data.repository.CrudRepository;
import com.devcaotics.doapet.entities.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{}
