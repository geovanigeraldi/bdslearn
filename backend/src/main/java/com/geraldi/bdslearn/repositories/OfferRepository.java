package com.geraldi.bdslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geraldi.bdslearn.entities.Offer;

public interface OfferRepository  extends JpaRepository<Offer, Long>{

}
