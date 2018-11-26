package com.epam.external.adapter;

import com.epam.contract.api.external.client.AccountApiClient;
import com.epam.contract.api.external.model.Account;
import com.epam.contract.api.external.server.AccountApiDelegate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@AllArgsConstructor
public class AccountApiAdapter implements AccountApiDelegate {

    private final AccountApiClient accountApiClient;

//    @Override
//    public ResponseEntity<Account> getAccount(String accountId, String clientId) {
//        return accountApiClient.getAccount(accountId, clientId);
//    }

    @Override
    public ResponseEntity<List<Account>> getAccounts(String  responses,
                                                     String  clientId,
                                                     String  ifModifiedSince,
                                                     String  accept) {
            return accountApiClient.getAccounts(responses,
                    clientId,
                    ifModifiedSince,
                    accept);
    }

//    @Override
//    public ResponseEntity<BooleanResponseProto.BooleanResponse> verifyEligibility(String clientId, String accountId, String cardType) {
//        return accountApiClient.verifyEligibility(clientId, accountId, cardType);
//    }
}
