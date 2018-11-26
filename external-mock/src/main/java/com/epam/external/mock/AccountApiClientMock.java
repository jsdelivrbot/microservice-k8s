package com.epam.external.mock;

import com.epam.contract.api.external.client.AccountApiClient;
import com.epam.contract.api.external.model.Account;
import com.epam.external.data.provider.AccountDataProvider;
import com.epam.external.data.provider.EligableDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Slf4j
public class AccountApiClientMock implements AccountApiClient {

    private final Map<String, List<Account>> accounts;
    private final Map<String, Boolean> eligibilities;

    public AccountApiClientMock(
            AccountDataProvider accountDataProvider,
            EligableDataProvider eligableDataProvider) {

        this.accounts = accountDataProvider.getAccountData();
        this.eligibilities = eligableDataProvider.getEligibilites();
    }

//    @Override
//    public ResponseEntity<List<Account>> getAccount(String accountId, @NotNull String clientId) {
//        LOG.debug("Mocking the 'getAccount' call on AccountApiClient." +
//                        "\n accountId: {}" +
//                        "\n clientId: {}",
//                accountId,
//                clientId);
//
//        if (!accounts.containsKey(clientId)) {
//            LOG.debug("Not found any account for client with clientId: {}",
//                    clientId);
//            return getNotFoundResponse();
//        }
//
//        List<Account> clientAccounts = accounts.get(clientId);
//        Optional<List<Account>> accountResult = clientAccounts.getAccountList().stream().filter(item -> accountId.equals(item.getAccountId())).findFirst();
//
//        if (!accountResult.isPresent()) {
//            LOG.debug("Not found account with accountId: {} for the client," +
//                            "\n accounts: {}",
//                    accountId, clientAccounts);
//            return getNotFoundResponse();
//        }
//
//        return ResponseEntity.ok(accountResult.get());
//    }

    @Override
    public ResponseEntity<List<Account>> getAccounts(String responses, String clientId, String ifModifiedSince, String accept) {
        LOG.debug("Mocking the 'getAccounts' call on AccountApiClient." +
                        "\n clientId: {}",
                clientId);

        if (!accounts.containsKey(clientId)) {
            LOG.debug("Not found account with clientId: {}" +
                            "\n clientId: {}",
                    clientId);
            return getNotFoundResponse();
        }

        return ResponseEntity.ok(accounts.get(clientId));
    }

//    @Override
//    public ResponseEntity<Boolean> verifyEligibility(@NotNull String clientId, @NotNull String accountId, @NotNull String cardType) {
//        LOG.debug("Mocking the 'verifyEligibility' call on AccountApiClient." +
//                        "\n xClientID: {}" +
//                        "\n accountId: {}" +
//                        "\n cardType: {}",
//                clientId,
//                accountId,
//                cardType);
//
//        CardType type = CardType.fromValue(cardType);
//        if (isNull(type)) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        if (!accounts.containsKey(clientId)) {
//            LOG.debug("Not found account with clientId: {}" +
//                            "\n xClientID: {}",
//                    clientId);
//            return getNotFoundResponse();
//        }
//
//        AccountProto.Accounts clientAccounts = accounts.get(clientId);
//        Optional<String> checkedAccountId = clientAccounts.getAccountList().stream()
//                .map(AccountProto.Account::getAccountId)
//                .filter(id -> accountId.equals(id))
//                .findAny();
//
//        if (!checkedAccountId.isPresent()) {
//            LOG.debug("Not found account with accountId: {} for the client",
//                    accountId);
//            return getNotFoundResponse();
//        }
//
//        String accountIdValue = checkedAccountId.get();
//        if (!eligibilities.containsKey(accountIdValue)) {
//            LOG.debug("Not found account with accountId: {} in eligible accounts" +
//                    accountId);
//            return getNotFoundResponse();
//        }
//
//        LOG.debug("Mocking the 'verifyEligibility' call returned OK");
//        return ResponseEntity.ok(BooleanResponseProto.BooleanResponse.newBuilder().setValue(eligibilities.get(accountIdValue)).build());
//    }

    private ResponseEntity getNotFoundResponse() {
        return ResponseEntity.notFound().build();
    }
}