package com.epam.cardmanagement.card.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mainAccount;

    private String accountId;

    private String ownerId;

    private String clientId;

    private String lastFourDigits;

    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    private Integer expiryYear;

    private Integer expiryMonth;

    private Boolean eActivation;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cardEntity", cascade = CascadeType.ALL)
    private CardDetailsMailAddressEntity mailAddress;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cardEntity", cascade = CascadeType.ALL)
    private CardDetailsPinAddressEntity pinAddress;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cardEntity", cascade = CascadeType.ALL)
    private CardDetailsInvoiceAddressEntity invoiceAddress;
}
