package com.devcaotics.doapet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devcaotics.doapet.entities.AdoptionApplication;

@Repository
public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication, Long>
{}
