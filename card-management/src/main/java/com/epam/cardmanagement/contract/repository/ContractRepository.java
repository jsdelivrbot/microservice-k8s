package com.epam.cardmanagement.contract.repository;

import com.epam.cardmanagement.contract.model.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
}
