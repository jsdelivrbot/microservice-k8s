package com.epam.external.data.provider.impl;


import com.epam.contract.api.external.model.Account;
import com.epam.external.data.provider.AccountDataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultAccountDataProvider implements AccountDataProvider {

    @Override
    public Map<String, List<Account>> getAccountData() {
        Map<String, List<Account>> accounts = new HashMap<>();

//        accounts.put("1", AccountProto.Accounts.newBuilder().addAccount(AccountProto.Account.newBuilder().setAccountId("11").setAccountType(AccountType.CHECKING_ACCOUNT).build()).build());
//
//        accounts.put("2", AccountProto.Accounts.newBuilder()
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("21").setAccountType(AccountType.CHECKING_ACCOUNT).build())
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("22").setAccountType(AccountType.SAVINGS_ACCOUNT).build())
//            .build());
//
//        accounts.put("3", AccountProto.Accounts.newBuilder()
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("31").setAccountType(AccountType.CHECKING_ACCOUNT).build())
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("32").setAccountType(AccountType.SAVINGS_ACCOUNT).build())
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("33").setAccountType(AccountType.CHECKING_ACCOUNT).build())
//                .build());
//
//        accounts.put("4", AccountProto.Accounts.newBuilder().build());
//
//        accounts.put("543210", AccountProto.Accounts.newBuilder()
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("12345678-87654321").setAccountType(AccountType.CURRENT_ACCOUNT).build())
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("12345678-11111111").setAccountType(AccountType.SBL_ACCOUNT).build())
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("12345678-22222222").setAccountType(AccountType.CURRENT_ACCOUNT).build())
//            .build());
//
//        accounts.put("543211", AccountProto.Accounts.newBuilder()
//                .addAccount(AccountProto.Account.newBuilder().setAccountId("12345678-12345678").setAccountType(AccountType.CURRENT_ACCOUNT).build())
//            .build());

        return accounts;
    }
}
