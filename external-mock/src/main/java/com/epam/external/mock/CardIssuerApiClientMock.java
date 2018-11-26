package com.epam.external.mock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardIssuerApiClientMock /*implements CardIssuerApiClient*/ {

//    private final Map<String, ClientDataProto.ClientData> clientDataStore;
//    private final Map<CardRequest, CardDetailsProto.CardDetails> cardDetailsStore;
//
//    public CardIssuerApiClientMock(
//            ClientDataProvider clientDataProvider,
//            CardCreationDataProvider cardCreationDataProvider) {
//
//        this.clientDataStore = clientDataProvider.getClientData();
//        this.cardDetailsStore = cardCreationDataProvider.getCardDetails();
//    }
//
//    @Override
//    public ResponseEntity<StatementProto.Statements> getStatements(@NotNull String clientId, String cardId, String period) {
//        // TODO
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<CardDetailsProto.CardDetails> createCard(CardCreationRequestProto.CardCreationRequest body, @NotNull String clientId) {
//        LOG.debug("Mocking 'createCard' call in CardIssuerApiClient." +
//                        "\n clientId: {}\n body: {}",
//                clientId,
//                body);
//
//        if (!clientDataStore.containsKey(clientId)) {
//            return getNotFoundResponse();
//        }
//
//        CardRequest request = new CardRequest(clientId, null /*TODO*/);
//
//        if (!cardDetailsStore.containsKey(request)) {
//            return getNotFoundResponse();
//        }
//
//        return ResponseEntity.ok(cardDetailsStore.get(request));
//    }
//
//    private ResponseEntity<CardDetailsProto.CardDetails> getNotFoundResponse() {
//        return ResponseEntity.notFound().build();
//    }
}
