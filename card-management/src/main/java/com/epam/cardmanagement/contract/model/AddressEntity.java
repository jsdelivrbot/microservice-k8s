package com.epam.cardmanagement.contract.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "address")
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String state;
    private String city;
    private String street1;
    private String street2;
}
