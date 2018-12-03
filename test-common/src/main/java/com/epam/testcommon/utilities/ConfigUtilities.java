package com.epam.testcommon.utilities;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ConfigUtilities {

    private static final Properties environmentConfiguration;

    static {
        environmentConfiguration = loadProperties();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static String getAccountServiceUri() {
        return environmentConfiguration.getProperty(Constants.ACCOUNT_SERVICE_NAME);
    }

    public static int getAccountServicePort() {
        return Integer.parseInt(environmentConfiguration.getProperty(Constants.ACCOUNT_SERVICE_PORT));
    }

    public static String getAcpServiceUri() {
        return environmentConfiguration.getProperty(Constants.ACP_SERVICE_NAME);
    }

    public static int getAcpServicePort() {
        return Integer.parseInt(environmentConfiguration.getProperty(Constants.ACP_SERVICE_PORT));
    }

    public static String getAlfaServiceUri() {
        return environmentConfiguration.getProperty(Constants.ALFA_SERVICE_NAME);
    }

    public static int getAlfaServicePort() {
        return Integer.parseInt(environmentConfiguration.getProperty(Constants.ALFA_SERVICE_PORT));
    }

    public static String getBetaServiceUri() {
        return environmentConfiguration.getProperty(Constants.BETA_SERVICE_NAME);
    }

    public static int getCardManagementServicePort() {
        return Integer.parseInt(environmentConfiguration.getProperty(Constants.CARD_MANAGEMENT_SERVICE_PORT));
    }

    public static String getCardManagementServiceUri() {
        return environmentConfiguration.getProperty(Constants.CARD_MANAGEMENT_SERVICE_NAME);
    }

    public static int getBetaServicePort() {
        return Integer.parseInt(environmentConfiguration.getProperty(Constants.BETA_SERVICE_PORT));
    }

    public static String getExchangeRateServiceUri() {
        return environmentConfiguration.getProperty(Constants.EXCHANGE_RATE_SERVICE_NAME);
    }

    public static int getExchangeRateServicePort() {
        return Integer.parseInt(environmentConfiguration.getProperty(Constants.EXCHANGE_RATE_SERVICE_PORT));
    }

    public static String getGrafanaURL() {
        return environmentConfiguration.getProperty("grafana-url");
    }

    private static Properties loadProperties() {
        Properties prop = new Properties();
        String testEnvironmentName = Constants.validateAndGetTestEnvironment();
        String propertiesFileName = "environmentProperties/" + testEnvironmentName + "-config.properties";
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream(propertiesFileName);
            prop.load(inputStream);
        } catch (IOException e) {
            LOG.error("Error loading the " + propertiesFileName + " file", e);
        }
        return prop;
    }
}
