package com.geraldi.bdslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geraldi.bdslearn.entities.Topic;

public interface TopicRepository  extends JpaRepository<Topic, Long>{

}
