package com.epam.cardmanagement.card.converter;

import com.epam.cardmanagement.card.repository.model.CardEntity;
import com.epam.cardmanagement.card.repository.model.CardStatus;
import com.epam.contract.api.card_management.model.*;
import org.springframework.core.convert.converter.Converter;

public class CardDetailsEntityToCardDetailsConverter implements Converter<CardEntity, CardDetails> {

    @Override
    public CardDetails convert(CardEntity source) {
//        Address mailAddress = new Address()
//                .city(source.getMailAddress().getCity())
//                .country(source.getMailAddress().getCountry())
//                .state(source.getMailAddress().getState())
//                .street1(source.getMailAddress().getStreet1())
//                .street2(source.getMailAddress().getStreet2());
//
//        Address pinAddress = new Address()
//                .city(source.getMailAddress().getCity())
//                .country(source.getPinAddress().getCountry())
//                .state(source.getPinAddress().getState())
//                .street1(source.getPinAddress().getStreet1())
//                .street2(source.getPinAddress().getStreet2());
//
//        Address invoiceAddress = new Address()
//                .city(source.getInvoiceAddress().getCity())
//                .country(source.getInvoiceAddress().getCountry())
//                .state(source.getInvoiceAddress().getState())
//                .street1(source.getInvoiceAddress().getStreet1())
//                .street2(source.getInvoiceAddress().getStreet2());

        CardDetails cardDetails = new CardDetails()
                .mainAccount(source.getMainAccount())
                .accountId(new CardDetailsAccountId().accountNumber(source.getAccountId()))
                .activated(CardStatus.ACTIVATED.equals(source.getCardStatus()))
                .eActivation(source.getEActivation())
                .serialNumber(source.getSerialNumber())
                //.mailAddress(mailAddress)
                //.pinAddress(pinAddress)
                //.invoiceAddress(invoiceAddress)
        ;

        return cardDetails;
    }
}
