package com.epam.cardmanagement.dao.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String iso;
    private String displayName;
    private String regionCode;
    private Boolean blockedByDefault;
    private boolean customizable;

}
