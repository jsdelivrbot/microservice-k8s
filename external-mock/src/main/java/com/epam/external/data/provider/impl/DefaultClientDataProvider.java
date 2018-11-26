package com.epam.external.data.provider.impl;

import com.epam.contract.api.external.model.ClientData;
import com.epam.external.data.provider.ClientDataProvider;

import java.util.HashMap;
import java.util.Map;

public class DefaultClientDataProvider implements ClientDataProvider {

    @Override
    public Map<String, ClientData> getClientData() {
        Map<String, ClientData> clientDataMap = new HashMap<>();

//        clientDataMap.put("1", new ClientData().set.newBuilder().setClientId("1").build());
//        clientDataMap.put("2", ClientDataProto.ClientData.newBuilder().setClientId("2").build());
//        clientDataMap.put("3", ClientDataProto.ClientData.newBuilder().setClientId("3").build());
//        clientDataMap.put("4", ClientDataProto.ClientData.newBuilder().setClientId("4").build());
//        clientDataMap.put("543210", ClientDataProto.ClientData.newBuilder().setClientId("543210").build());
//        clientDataMap.put("543211", ClientDataProto.ClientData.newBuilder().setClientId("543211").build());

        return clientDataMap;
    }
}
