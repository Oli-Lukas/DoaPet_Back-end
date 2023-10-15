package com.devcaotics.doapet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devcaotics.doapet.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{}
