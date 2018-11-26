package com.epam.external.data.provider.impl;

import com.epam.external.data.provider.ClientVerificationDataProvider;

import java.util.HashMap;
import java.util.Map;

public class DefaultClientVerificationDataProvider implements ClientVerificationDataProvider {

    @Override
    public Map<String, Boolean> getVerifications() {
        Map<String, Boolean> verifications = new HashMap<>(4);

        verifications.put("1", true);
        verifications.put("2", true);
        verifications.put("3", true);
        verifications.put("4", false);

        return verifications;
    }
}
