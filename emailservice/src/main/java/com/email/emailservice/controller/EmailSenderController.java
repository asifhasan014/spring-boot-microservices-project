package com.email.emailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.emailservice.dto.EmailSenderDto;
import com.email.emailservice.service.EmailSenderService;

@RestController
@RequestMapping("/email")
public class EmailSenderController {
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@PostMapping("/mailSend")
	public String createEmployee(@RequestBody EmailSenderDto emailSenderDto) {
		return emailSenderService.emailSender(emailSenderDto);
	}
	
}
