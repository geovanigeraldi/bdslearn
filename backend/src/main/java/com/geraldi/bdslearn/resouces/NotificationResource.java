package com.geraldi.bdslearn.resouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geraldi.bdslearn.dto.NotificationDTO;
import com.geraldi.bdslearn.services.NotificationService;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationResource {

	@Autowired
	private NotificationService service;
	
	@GetMapping
	public ResponseEntity<Page<NotificationDTO>> findall(
			@RequestParam(value = "unreadOnly", defaultValue = "false") Boolean unreadOnly,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size	
			)	{
		
		PageRequest pageRequest = PageRequest.of(page, size);		
		Page<NotificationDTO> list = service.notificationCurrentUser(pageRequest, unreadOnly);		
		return ResponseEntity.ok().body(list);
	}
	
}