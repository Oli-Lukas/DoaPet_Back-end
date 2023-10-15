package com.devcaotics.doapet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devcaotics.doapet.entities.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>
{}
