package com.epam.cardmanagement.dao;

import com.epam.cardmanagement.dao.model.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

    RegionEntity findByCode(String code);
}
