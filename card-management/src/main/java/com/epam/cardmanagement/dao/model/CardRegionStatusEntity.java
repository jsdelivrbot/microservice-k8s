package com.epam.cardmanagement.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "card_region_status")
@Data
@NoArgsConstructor
public class CardRegionStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardId;
    private String regionCode;
    private boolean blocked;
    private LocalDate expirationDate;
}
