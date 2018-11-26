package com.epam.cardmanagement.contract.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "card_fee")
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class CardFeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yearly_fee_amount_id")
    private AmountEntity yearlyFeeAmount;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monthly_fee_amount_id")
    private AmountEntity monthlyFeeAmount;

    private double debtInterestRate;
    private double creditInterestRate;
}
