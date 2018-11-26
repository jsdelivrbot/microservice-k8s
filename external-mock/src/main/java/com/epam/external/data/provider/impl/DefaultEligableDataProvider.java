package com.epam.external.data.provider.impl;

import com.epam.external.data.provider.EligableDataProvider;

import java.util.HashMap;
import java.util.Map;

public class DefaultEligableDataProvider implements EligableDataProvider {

    @Override
    public Map<String, Boolean> getEligibilites() {
        Map<String, Boolean> eligibilities = new HashMap<>();

        eligibilities.put("11", true);

        eligibilities.put("21", true);
        eligibilities.put("22", false);

        eligibilities.put("31", true);
        eligibilities.put("32", false);
        eligibilities.put("33", true);
        eligibilities.put("12345678-87654321", true);
        eligibilities.put("12345678-22222222", false);
        eligibilities.put("12345678-12345678", true);
        eligibilities.put("12345678-11111111", true);

        return eligibilities;
    }

}
