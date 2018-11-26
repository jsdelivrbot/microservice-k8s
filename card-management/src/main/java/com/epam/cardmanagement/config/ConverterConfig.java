//package com.epam.cardmanagement.config;
//
//import com.epam.cardmanagement.dao.model.*;
//import com.epam.contract.api.card_management.model.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//
//import static com.epam.cardmanagement.service.DefaultCardsApiDelegate.BANK_CONFIG_EXPIRATION_DATE;
//
//@Configuration
//public class ConverterConfig {
//
//    @Bean
//    public Converter<CardEntity, Card> cardEntityToCardConverter() {
//        return new Converter<CardEntity, Card>() {
//            @Override
//            public Card convert(CardEntity source) {
//                return new Card()
//                        .clientId(source.getClientId())
//                        .lastFourDigits(source.getLastFourDigits());
//            }
//        };
//    }
//
//    @Bean
//    public Converter<RegionEntity, DefaultRegionGeoblock> regionEntityToDefaultRegionGeoblockConverter() {
//        return new Converter<RegionEntity, DefaultRegionGeoblock>() {
//            @Override
//            public DefaultRegionGeoblock convert(RegionEntity source) {
//                return new DefaultRegionGeoblock()
//                        .code(source.getCode())
//                        .name(source.getName())
//                        .displayName(source.getDisplayName())
//                        .blockedByDefault(source.isBlockedByDefault())
//                        .customizable(source.isCustomizable());
//            }
//        };
//    }
//
//    @Bean
//    public Converter<CountryEntity, DefaultCountryGeoblock> countryEntityToDefaultCountryGeoblockConverter() {
//        return new Converter<CountryEntity, DefaultCountryGeoblock>() {
//            @Override
//            public DefaultCountryGeoblock convert(CountryEntity source) {
//                return new DefaultCountryGeoblock()
//                        .countryIso(source.getIso())
//                        .regionCode(source.getRegionCode())
//                        .displayName(source.getDisplayName())
//                        .blockedByDefault(source.getBlockedByDefault())
//                        .customizable(source.isCustomizable());
//            }
//        };
//    }
//
//    @Bean
//    public Converter<CardCountryStatusEntity, CountryGeoblock> cardCountryStatusEntityToCountryGeoblockConverter() {
//        return new Converter<CardCountryStatusEntity, CountryGeoblock>() {
//            @Override
//            public CountryGeoblock convert(CardCountryStatusEntity source) {
//                return new CountryGeoblock()
//                        .cardId(source.getCardId())
//                        .countryIso(source.getCountryIso())
//                        .blocked(source.isBlocked())
//                        .expirationDate(!BANK_CONFIG_EXPIRATION_DATE.equals(source.getExpirationDate()) ? source.getExpirationDate() : null);
//            }
//        };
//    }
//
//    @Bean
//    public Converter<CardRegionStatusEntity, RegionGeoblock> cardRegionStatusEntityToRegionGeoblockConverter() {
//        return new Converter<CardRegionStatusEntity, RegionGeoblock>() {
//            @Override
//            public RegionGeoblock convert(CardRegionStatusEntity source) {
//                return new RegionGeoblock()
//                        .cardId(source.getCardId())
//                        .regionCode(source.getRegionCode())
//                        .blocked(source.isBlocked())
//                        .expirationDate(!BANK_CONFIG_EXPIRATION_DATE.equals(source.getExpirationDate()) ? source.getExpirationDate() : null);
//            }
//        };
//    }
//}
