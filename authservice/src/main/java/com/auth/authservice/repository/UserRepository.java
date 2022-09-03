package com.auth.authservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.auth.authservice.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, Long>{
	public User findByUsername(String username);
}
