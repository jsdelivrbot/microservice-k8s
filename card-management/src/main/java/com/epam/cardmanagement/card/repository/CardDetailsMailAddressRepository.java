package com.epam.cardmanagement.card.repository;

import com.epam.cardmanagement.card.repository.model.CardDetailsMailAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDetailsMailAddressRepository extends JpaRepository<CardDetailsMailAddressEntity, Long> {
}
