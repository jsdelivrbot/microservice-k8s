package com.epam.cardmanagement.dao;

import com.epam.cardmanagement.dao.model.CardCountryStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface CardCountryStatusRepository extends JpaRepository<CardCountryStatusEntity, Long> {

    CardCountryStatusEntity findByCardIdAndCountryIsoAndExpirationDateAfter(String cardId, String countryIso, LocalDate expirationDate);

    List<CardCountryStatusEntity> findByCardIdAndExpirationDateAfter(String cardId, LocalDate expirationDate);

    @Modifying
    @Transactional
    @Query("delete from CardCountryStatusEntity where expirationDate < ?1 ")
    int deleteExpired(LocalDate expiration);

}
