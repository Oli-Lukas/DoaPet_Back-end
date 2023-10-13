package com.devcaotics.doapet.repositories;

import org.springframework.data.repository.CrudRepository;
import com.devcaotics.doapet.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long>
{}
