package com.epam.cardmanagement.card.repository;

import com.epam.cardmanagement.card.repository.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDetailsRepository extends JpaRepository<CardEntity, Long> {

}
