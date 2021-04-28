package com.geraldi.bdslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geraldi.bdslearn.dto.NotificationDTO;
import com.geraldi.bdslearn.entities.Notification;
import com.geraldi.bdslearn.entities.User;
import com.geraldi.bdslearn.repositories.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository repository;

	@Autowired
	private AuthService authservice;
	
	public Page<NotificationDTO> notificationCurrentUser(Pageable pageable, Boolean unreadOnly){
		User user = authservice.authenticated();
		
		Page<Notification> page = repository.find(user, unreadOnly, pageable);
		return page.map(x -> new NotificationDTO(x));
	}
	
}