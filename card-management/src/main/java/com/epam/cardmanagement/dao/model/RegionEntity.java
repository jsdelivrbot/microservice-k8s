package com.epam.cardmanagement.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "region")
@Data
@NoArgsConstructor
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String displayName;
    private boolean blockedByDefault;
    private boolean customizable;

}
