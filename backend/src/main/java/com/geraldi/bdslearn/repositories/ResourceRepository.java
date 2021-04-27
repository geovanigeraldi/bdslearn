package com.geraldi.bdslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geraldi.bdslearn.entities.Resource;

public interface ResourceRepository  extends JpaRepository<Resource, Long>{

}
