package com.geraldi.bdslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geraldi.bdslearn.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
