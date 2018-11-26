package com.epam.cardmanagement.dao;


import com.epam.cardmanagement.dao.model.CardRegionStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface CardRegionStatusRepository extends JpaRepository<CardRegionStatusEntity, Long> {

    CardRegionStatusEntity findByCardIdAndRegionCodeAndExpirationDateAfter(String cardId, String regionCode, LocalDate expirationDate);

    List<CardRegionStatusEntity> findByCardIdAndExpirationDateAfter(String cardId, LocalDate expirationDate);

    @Modifying
    @Transactional
    @Query("delete from CardRegionStatusEntity where expirationDate < ?1 ")
    int deleteExpired(LocalDate expiration);
}
