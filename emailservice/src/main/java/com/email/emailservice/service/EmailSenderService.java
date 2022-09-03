package com.email.emailservice.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.emailservice.dto.EmailSenderDto;
import com.email.emailservice.model.DatabaseSequence;
import com.email.emailservice.model.Email;
import com.email.emailservice.repository.EmailSenderRepository;

@Service
public class EmailSenderService {

	@Autowired
	private EmailSenderRepository emailRepository;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private JavaMailSender mailSender;

	public String emailSender(EmailSenderDto emailSenderDto) {
		String message = "";
		String body = "Hi Sir/Madam, " + emailSenderDto.getMessage() + " ,Thank you";

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom("asifhasan014@gmail.com");
		simpleMailMessage.setTo(emailSenderDto.getToEmail());
		simpleMailMessage.setSubject("Reminder");
		simpleMailMessage.setText(body);
		
		mailSender.send(simpleMailMessage);

		Email email = new Email();
		email.setId(generateSequence(Email.SEQUENCE_NAME));
		email.setFrom("asifhasan018636223@gmail.com");
		email.setTo(emailSenderDto.getToEmail());
		email.setMessage(emailSenderDto.getMessage());
		emailRepository.save(email);
		message = "Email send sccessfully";
		return message;
	}

	private long generateSequence(String seqName) {
		DatabaseSequence counter = mongoOperations.findAndModify(new Query(Criteria.where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
