package com.email.emailservice.dto;

import lombok.Data;

@Data
public class EmailSenderDto {
    private Long id;
	private String toEmail;
	private String message;
}
