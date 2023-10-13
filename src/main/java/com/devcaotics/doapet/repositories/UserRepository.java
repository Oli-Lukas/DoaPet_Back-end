package com.devcaotics.doapet.repositories;

import org.springframework.data.repository.CrudRepository;
import com.devcaotics.doapet.entities.User;

public interface UserRepository extends CrudRepository<User, Long>
{}
