package com.epam.external.data.provider;

import com.epam.contract.api.external.model.ClientData;

import java.util.Map;

public interface ClientDataProvider {

    Map<String, ClientData> getClientData();
}
