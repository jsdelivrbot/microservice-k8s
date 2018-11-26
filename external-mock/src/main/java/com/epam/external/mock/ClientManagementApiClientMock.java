package com.epam.external.mock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientManagementApiClientMock /*implements ClientManagementApiClient*/ {

//    private final Map<String, ClientDataProto.ClientData> clientDataStore;
//
//    public ClientManagementApiClientMock(ClientDataProvider clientDataProvider) {
//        this.clientDataStore = clientDataProvider.getClientData();
//    }
//
//    @Override
//    public ResponseEntity<ClientDataProto.ClientData> getClientData(String clientId) {
//        LOG.debug("Mocking 'getClientData' call in ClientManagementApiClient." +
//                        "\n clientId: {}",
//                clientId);
//
//        if (!clientDataStore.containsKey(clientId)) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(clientDataStore.get(clientId));
//    }
}
