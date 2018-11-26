package com.epam.cardmanagement.card;

import com.epam.contract.api.card_management.model.*;
import com.epam.contract.api.card_management.server.CardsApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CardAdapter implements CardsApiDelegate {

    private final CardService cardService;

    @Override
    public ResponseEntity<Void> createCard(CardDetails cardDetails) {
        cardService.createCard(cardDetails);

        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> updateCardDetails(String cardId, CardUpdate body) {
        return notImplemented();
    }

    @Override
    public ResponseEntity<List<Card>> getCards(String  responses,
                                               String  xClientID,
                                               String  ifModifiedSince,
                                               String  accept,
                                               String  cardStatus) {
        List<Card> cards = cardService.getCards(xClientID, cardStatus);

        return ResponseEntity.ok(cards);
    }

    @Override
    public ResponseEntity<CardDetails> getCardDetails(String cardId, String responses, String ifModifiedSince, String accept) {
        return notImplemented();
    }

    private <T> ResponseEntity<T> notImplemented() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
