package com.geraldi.bdslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geraldi.bdslearn.entities.Lesson;

public interface LessonRepository  extends JpaRepository<Lesson, Long>{

}
