package com.auth.authservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "User")
public class User{
	@Transient
    public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	private Long id;
	
	private String username;
	
	private String password;
}
