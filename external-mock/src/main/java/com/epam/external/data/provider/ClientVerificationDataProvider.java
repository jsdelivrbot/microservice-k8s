package com.epam.external.data.provider;

import java.util.Map;

public interface ClientVerificationDataProvider {

    Map<String, Boolean> getVerifications();
}
