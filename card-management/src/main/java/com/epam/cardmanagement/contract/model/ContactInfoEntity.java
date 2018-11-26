package com.epam.cardmanagement.contract.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "contact_info")
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class ContactInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_number_id")
    private PhoneNumberEntity voicePhone;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sms_number_id")
    private PhoneNumberEntity smsPhone ;
    private String email;

}
