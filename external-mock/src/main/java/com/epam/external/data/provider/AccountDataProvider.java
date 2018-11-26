package com.epam.external.data.provider;

import com.epam.contract.api.external.model.Account;

import java.util.List;
import java.util.Map;

public interface AccountDataProvider {

    Map<String, List<Account>> getAccountData();
}
