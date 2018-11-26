package com.epam.cardmanagement.dao;

import com.epam.cardmanagement.dao.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    CountryEntity findByIso(String iso);
}
