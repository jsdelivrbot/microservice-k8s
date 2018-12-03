package com.epam.testcommon.utilities;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Constants {
    public static final String TEST_TYPE_ENVIRONMENT_VARIABLE_NAME = "TEST_TYPE";
    public static final String UNIT_GROUP = "Unit";
    public static final String COMPONENT_GROUP = "Component";
    public static final String COMPONENT_PERFORMANCE_GROUP = "Component-Performance";
    public static final String PERFORMANCE_GROUP = "Performance";
    public static final String API_GROUP = "Api";
    public static final String SMOKE_GROUP = "Smoke";
    public static final String JMETER_HOME = getJmeterHome();
    public static final String TEST_TARGETS_ENVIRONMENT_VARIABLE_NAME = "TEST_TARGETS";
    public static final String ACP_SERVICE_NAME = "acp-service";
    public static final String ACP_SERVICE_PORT = "acp-service-port";
    public static final String ACCOUNT_SERVICE_NAME = "account-service";
    public static final String ACCOUNT_SERVICE_PORT = "account-service-port";
    public static final String ALFA_SERVICE_NAME = "alfa-service";
    public static final String ALFA_SERVICE_PORT = "alfa-service-port";
    public static final String BETA_SERVICE_NAME = "beta-service";
    public static final String BETA_SERVICE_PORT = "beta-service-port";
    public static final String CARD_MANAGEMENT_SERVICE_NAME = "card-management-service";
    public static final String CARD_MANAGEMENT_SERVICE_PORT = "card-management-service-port";
    public static final String EXCHANGE_RATE_SERVICE_NAME = "exchange-rate-service";
    public static final String EXCHANGE_RATE_SERVICE_PORT = "exchange-rate-service-port";
    public static final String TEST_ENV_ENVIRONMENT_VARIABLE_NAME = "TEST_ENV";
    public static final String DEFAULT_ENV = "dev-local";
    public static final String CI_ENV = "CI";
    private static final String[] VALID_ENVIRONMENT_NAMES = {DEFAULT_ENV, CI_ENV};

    private Constants(){}

    public static final String[] VALID_TEST_TYPES = {
            UNIT_GROUP,
            COMPONENT_GROUP,
            COMPONENT_PERFORMANCE_GROUP,
            PERFORMANCE_GROUP,
            API_GROUP,
            SMOKE_GROUP
    };

    public static String validateAndGetTestType() {
        String toValidate = System.getenv(TEST_TYPE_ENVIRONMENT_VARIABLE_NAME);
        return validateAndGetVariable(VALID_TEST_TYPES, TEST_TYPE_ENVIRONMENT_VARIABLE_NAME, toValidate);
    }

    public static final String[] VALID_SERVICE_NAMES = {
            ACCOUNT_SERVICE_NAME,
            ACP_SERVICE_NAME,
            ALFA_SERVICE_NAME,
            BETA_SERVICE_NAME,
            EXCHANGE_RATE_SERVICE_NAME
    };

    public static String[] validateAndGetTestTargets() {
        String[] testTargets = System.getenv(TEST_TARGETS_ENVIRONMENT_VARIABLE_NAME).split(",");
        for (String testTarget : testTargets) {
            validateAndGetVariable(VALID_SERVICE_NAMES, TEST_TARGETS_ENVIRONMENT_VARIABLE_NAME, testTarget);
        }
        return testTargets;
    }

    public static String validateAndGetTestEnvironment() {
        String toValidate = System.getenv(TEST_ENV_ENVIRONMENT_VARIABLE_NAME);
        if (null == toValidate || toValidate.isEmpty()) {
            toValidate = DEFAULT_ENV;
        }
        return validateAndGetVariable(VALID_ENVIRONMENT_NAMES, TEST_ENV_ENVIRONMENT_VARIABLE_NAME, toValidate);
    }

    public static String validateAndGetVariable(String[] validValues, String environmentVariableName, String toValidate) {
        String validNamesText = StringUtils.join(validValues, ", ");
        if (null == toValidate || toValidate.isEmpty()) {
            LOG.error("Missing environment variable '" + environmentVariableName + "' (possible values: "
                    + validNamesText
                    + ")");
            System.exit(1);
        } else if (!ArrayUtils.contains(validValues, toValidate)) {
            LOG.error(
                    "Invalid value for environment variable '" + environmentVariableName + "' (possible values: "
                            + validNamesText +
                            ") " +
                            "value was: " + toValidate);
            System.exit(1);
        }
        return toValidate;
    }

    private static String getJmeterHome() {
        String jmeterHome = System.getenv("JMETER_HOME");
        if (null == jmeterHome || jmeterHome.isEmpty()) {
            return "";
        }
        return jmeterHome;
    }
}
