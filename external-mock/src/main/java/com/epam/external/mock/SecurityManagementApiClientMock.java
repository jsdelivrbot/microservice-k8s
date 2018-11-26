package com.epam.external.mock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityManagementApiClientMock /*implements SecurityManagementApiClient*/ {

//    private final Map<String, Boolean> verifications;
//
//    public SecurityManagementApiClientMock(ClientVerificationDataProvider clientVerificationDataProvider) {
//        this.verifications = clientVerificationDataProvider.getVerifications();
//    }
//
//
//    @Override
//    public ResponseEntity<BooleanResponseProto.BooleanResponse> verifyClient(ClientDataProto.ClientData body, @NotNull String verificationType) {
//        LOG.debug("Mocking 'verifyClient' in SecurityCheckApiClient." +
//                        "\n body: {}\n verificationType: {}",
//                body,
//                verificationType);
//
//        String clientId = body.getClientId();
//
//        if (isNull(clientId)) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        if (!verifications.containsKey(clientId)) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(BooleanResponseProto.BooleanResponse.newBuilder().setValue(verifications.get(clientId)).build());
//    }
}
