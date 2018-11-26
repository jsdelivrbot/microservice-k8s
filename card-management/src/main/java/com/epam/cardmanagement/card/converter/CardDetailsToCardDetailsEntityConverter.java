package com.epam.cardmanagement.card.converter;

import com.epam.cardmanagement.card.repository.model.*;
import com.epam.contract.api.card_management.model.CardDetails;
import org.springframework.core.convert.converter.Converter;

import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class CardDetailsToCardDetailsEntityConverter implements Converter<CardDetails, CardEntity> {

    private static final DateTimeFormatter EXPIRATION_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM");

    @Override
    public CardEntity convert(CardDetails source) {
        CardDetailsMailAddressEntity mailAddress = new CardDetailsMailAddressEntity();
        mailAddress.setCity(source.getMailAddress().getCity());
        mailAddress.setCountry(source.getMailAddress().getCountry());
        mailAddress.setState(source.getMailAddress().getState());
        mailAddress.setStreet1(source.getMailAddress().getStreet1());
        mailAddress.setStreet2(source.getMailAddress().getStreet2());

        CardDetailsPinAddressEntity pinAddress = new CardDetailsPinAddressEntity();
        pinAddress.setCity(source.getPinAddress().getCity());
        pinAddress.setCountry(source.getPinAddress().getCountry());
        pinAddress.setState(source.getPinAddress().getState());
        pinAddress.setStreet1(source.getPinAddress().getStreet1());
        pinAddress.setStreet2(source.getPinAddress().getStreet2());

        CardDetailsInvoiceAddressEntity invoiceAddress = new CardDetailsInvoiceAddressEntity();
        invoiceAddress.setCity(source.getInvoiceAddress().getCity());
        invoiceAddress.setCountry(source.getInvoiceAddress().getCountry());
        invoiceAddress.setState(source.getInvoiceAddress().getState());
        invoiceAddress.setStreet1(source.getInvoiceAddress().getStreet1());
        invoiceAddress.setStreet2(source.getInvoiceAddress().getStreet2());

        CardEntity cardDetails = new CardEntity();
        //cardDetails.setClientId(source.getClientId());
        //cardDetails.setLastFourDigits(source.getLastFourDigits());
        //cardDetails.setOwnerId(source.getOwnerId());
        cardDetails.setMainAccount(source.getMainAccount());
        cardDetails.setAccountId(source.getAccountId().getAccountNumber());
        cardDetails.setCardStatus(getCardStatus(source.isActivated()));
        cardDetails.setEActivation(source.isEActivation());
        cardDetails.setSerialNumber(source.getSerialNumber());
        //TemporalAccessor temporalAccessor = EXPIRATION_DATE_FORMAT.parse(String.format("%s-%s", source.getExpiryYear(), source.getExpiryMonth()));
        //cardDetails.setExpiryYear(Year.from(temporalAccessor).getValue());
        //cardDetails.setExpiryMonth(Month.from(temporalAccessor).getValue());
        cardDetails.setMailAddress(mailAddress);
        cardDetails.setPinAddress(pinAddress);
        cardDetails.setInvoiceAddress(invoiceAddress);

        mailAddress.setCardEntity(cardDetails);
        pinAddress.setCardEntity(cardDetails);
        invoiceAddress.setCardEntity(cardDetails);
        return cardDetails;
    }

    private CardStatus getCardStatus(Boolean activated) {
        if (activated) {
            return CardStatus.ACTIVATED;
        }
        return CardStatus.NOT_ACTIVATED;
    }
}
