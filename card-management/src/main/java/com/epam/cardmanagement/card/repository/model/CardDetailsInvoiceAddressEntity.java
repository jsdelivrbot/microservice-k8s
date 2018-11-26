package com.epam.cardmanagement.card.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "card_details_invoice_address")
@Data
@NoArgsConstructor
@ToString(exclude = "cardEntity")
public class CardDetailsInvoiceAddressEntity {

    @Id
    @GeneratedValue(generator = "invoice_address_id_generator")
    @GenericGenerator(name = "invoice_address_id_generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "cardEntity"))
    private Long id;
    private String country;
    private String state;
    private String city;
    private String street1;
    private String street2;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardEntity cardEntity;
}
