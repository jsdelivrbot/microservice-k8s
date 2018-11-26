package com.epam.cardmanagement.config;

import com.epam.cardmanagement.card.CardAdapter;
import com.epam.cardmanagement.card.CardService;
import com.epam.cardmanagement.card.converter.CardDetailsEntityToCardDetailsConverter;
import com.epam.cardmanagement.card.converter.CardDetailsToCardDetailsEntityConverter;
import com.epam.cardmanagement.card.repository.CardDetailsRepository;
import com.epam.contract.api.card_management.server.CardsApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class CardConfig {

    @Bean
    public CardsApiController cardsApiController(CardAdapter cardAdapter) {
        return new CardsApiController(cardAdapter);
    }

    @Bean
    public CardAdapter cardAdapter(CardService cardService) {
        return new CardAdapter(cardService);
    }

    @Bean
    public CardService cardService(ConversionService conversionService,
                                   CardDetailsRepository cardDetailsRepository) {
        return new CardService(conversionService, cardDetailsRepository);
    }

    @Bean
    public CardDetailsToCardDetailsEntityConverter cardDetailsToCardDetailsEntityConverter() {
        return new CardDetailsToCardDetailsEntityConverter();
    }

    @Bean
    public CardDetailsEntityToCardDetailsConverter cardDetailsEntityToCardDetailsConverter() {
        return new CardDetailsEntityToCardDetailsConverter();
    }
}
