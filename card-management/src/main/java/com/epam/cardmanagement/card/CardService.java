package com.epam.cardmanagement.card;

import com.epam.cardmanagement.card.repository.CardDetailsRepository;
import com.epam.cardmanagement.card.repository.model.CardEntity;
import com.epam.contract.api.card_management.model.Card;
import com.epam.contract.api.card_management.model.CardDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CardService {

    private final ConversionService conversionService;
    private final CardDetailsRepository cardDetailsRepository;

    public void createCard(CardDetails cardDetails) {
        CardEntity cardEntity = conversionService.convert(cardDetails, CardEntity.class);
        cardDetailsRepository.save(cardEntity);
    }

    public List<Card> getCards(String  xClientID, String  cardStatus) {
        List<CardEntity> cardEntities = cardDetailsRepository.findAll();
        List<Card> cards = cardEntities.stream().map(c -> conversionService.convert(c, Card.class)).collect(Collectors.toList());
        return cards;
    }
}
