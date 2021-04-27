package com.geraldi.bdslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geraldi.bdslearn.entities.Notification;

public interface NotificationRepository  extends JpaRepository<Notification, Long>{

}

