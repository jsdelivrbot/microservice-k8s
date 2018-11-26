package com.epam.cardmanagement.contract.model;


import lombok.*;

import javax.persistence.*;

@Table(name = "phone_number")
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class PhoneNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String countryCode;
    private String areaCode;
    private String phoneNumber;
    private String extension;

}
