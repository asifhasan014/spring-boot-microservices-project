package com.auth.authservice.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.authservice.dto.UserDto;
import com.auth.authservice.model.DatabaseSequence;
import com.auth.authservice.model.User;
import com.auth.authservice.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserDto save(UserDto user) {
		User newUser = new User();
		newUser.setId(generateSequence(User.SEQUENCE_NAME));
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		userRepository.save(newUser);
		UserDto userResponse = new UserDto();
		userResponse.setUsername(newUser.getUsername());
		userResponse.setPassword(newUser.getPassword());
		
		return userResponse;
	}
	
	private long generateSequence(String seqName) {
		DatabaseSequence counter = mongoOperations.findAndModify(new Query(Criteria.where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
