package com.geraldi.bdslearn.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geraldi.bdslearn.dto.UserDTO;
import com.geraldi.bdslearn.entities.User;
import com.geraldi.bdslearn.repositories.UserRepository;
import com.geraldi.bdslearn.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authservice;

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		authservice.validateSelfOrAdmin(id);		
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("user not found: " + username);
			throw new UsernameNotFoundException("email not found");
		}
		logger.info("user found: " + username);
		return user;
	}

}