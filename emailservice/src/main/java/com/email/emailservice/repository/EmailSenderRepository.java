package com.email.emailservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.email.emailservice.model.Email;

public interface EmailSenderRepository extends MongoRepository<Email, Long>{

}
