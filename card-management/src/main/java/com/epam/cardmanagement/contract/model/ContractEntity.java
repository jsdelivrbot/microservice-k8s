package com.epam.cardmanagement.contract.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "contract")
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractId;
    private String clientId;
    private String accountId;
    private String cardId;
    private String documentId;
    private boolean eActivation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private CardOfferEntity offer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContactInfoEntity> contactInfo;

}
