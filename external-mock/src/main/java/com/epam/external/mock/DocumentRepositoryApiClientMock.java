package com.epam.external.mock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DocumentRepositoryApiClientMock /*implements DocumentRepositoryApiClient*/ {

//    private final Map<CardType, OfferDocumentLinkProto.OfferDocumentLinks> cardOfferLinks;
//
//    public DocumentRepositoryApiClientMock(DocumentLinkDataProvider documentLinkDataProvider) {
//        this.cardOfferLinks = documentLinkDataProvider.getOfferLinks();
//    }
//
//    @Override
//    public ResponseEntity<OfferDocumentLinkProto.OfferDocumentLinks> getCardOffers(@NotNull String cardType) {
//        LOG.debug("Mocking 'getCardOffers' call in DocumentRepositoryApiClient." +
//                        "\n cardType: {}",
//                cardType);
//
//        CardType cardTypeEnum = CardType.valueOf(cardType);
//
//        if (isNull(cardTypeEnum)) {
//            LOG.debug("cardType {} is invalid", cardType );
//            return ResponseEntity.badRequest().build();
//        }
//
//        if (!cardOfferLinks.containsKey(cardTypeEnum)) {
//            LOG.debug("No cardOffer found with cardType {} ", cardType );
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(cardOfferLinks.get(cardTypeEnum));
//    }
//
//
//    @Override
//    public ResponseEntity<OfferDocumentLinkProto.OfferDocumentLink> getCardTermsDocument(String offerId) {
//        LOG.debug("Mocking 'getCardTermsDocument' call in DocumentRepositoryApiClient." +
//                        "\n offerId: {}",
//                offerId);
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @Override
//    public ResponseEntity<DocumentLinkProto.DocumentLink> storeContract(ContractDetailsProto.ContractDetails body, @NotNull String clientId, @NotNull String offerId) {
//        LOG.debug("Mocking 'storeContract' call in DocumentRepositoryApiClient." +
//                        "\n body: {}\n clientId: {}\n offerId: {}",
//                body,
//                clientId,
//                offerId);
//
//        return ResponseEntity.ok(DocumentLinkProto.DocumentLink.newBuilder().setUrl("DocumentLink url").build());
//    }
}
