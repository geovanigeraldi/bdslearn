package com.geraldi.bdslearn.services;

import java.time.Instant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geraldi.bdslearn.dto.NotificationDTO;
import com.geraldi.bdslearn.entities.Deliver;
import com.geraldi.bdslearn.entities.Notification;
import com.geraldi.bdslearn.entities.User;
import com.geraldi.bdslearn.observers.DeliverRevisionObserver;
import com.geraldi.bdslearn.repositories.NotificationRepository;

@Service
public class NotificationService implements DeliverRevisionObserver {

	@Autowired
	private NotificationRepository repository;

	@Autowired
	private AuthService authservice;
	
	@Autowired
	private DeliverService deliverService;
	
	@PostConstruct
	private void initialize() { //executa com o objeto NotificationService for instanciado
		deliverService.subscribeDeliverRevisionObserver(this);
	}
	
	@Transactional(readOnly = true)
	public Page<NotificationDTO> notificationCurrentUser(Pageable pageable, Boolean unreadOnly){
		User user = authservice.authenticated();
		
		Page<Notification> page = repository.find(user, unreadOnly, pageable);
		return page.map(x -> new NotificationDTO(x));
	}
	
	@Transactional
	public void saveDeliverNotification(Deliver deliver) {
		Long sectionId = deliver.getLesson().getSection().getId();
		Long resourceId = deliver.getLesson().getSection().getResource().getId();
		Long offerId = deliver.getLesson().getSection().getResource().getOffer().getId();
		
		String route = "/offers/" + offerId + "/resources/" + resourceId + "/sections/" + sectionId;
		String text = deliver.getFeedback();
		Instant moment = Instant.now();
		User user = deliver.getEnrollment().getStudent();
		
		Notification notification = new Notification(null, text, moment, false, route, user);
		repository.save(notification);
	}

	@Override
	public void onSaveRevision(Deliver deliver) {
		saveDeliverNotification(deliver);		
	}
	
}