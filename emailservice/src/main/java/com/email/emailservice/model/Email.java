package com.email.emailservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Email")
public class Email {
	
	@Transient
    public static final String SEQUENCE_NAME = "email_sequence";
	
	@Id
	private Long id;
	private String from;
	private String to;
	private String message;
}
