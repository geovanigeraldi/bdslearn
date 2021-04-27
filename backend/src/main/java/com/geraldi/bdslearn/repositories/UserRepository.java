package com.geraldi.bdslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geraldi.bdslearn.entities.User;

public interface UserRepository  extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
