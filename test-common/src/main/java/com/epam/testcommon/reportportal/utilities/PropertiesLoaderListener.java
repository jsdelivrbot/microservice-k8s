package com.epam.testcommon.reportportal.utilities;

import com.epam.reportportal.guice.ConfigurationModule;
import com.epam.reportportal.guice.Injector;
import com.epam.reportportal.guice.ReportPortalClientModule;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGAgentModule;
import com.epam.reportportal.utils.properties.ListenerProperty;
import com.epam.reportportal.utils.properties.PropertiesLoader;
import com.epam.testcommon.utilities.Constants;
import lombok.extern.slf4j.Slf4j;
import rp.com.google.inject.Binder;
import rp.com.google.inject.Module;
import rp.com.google.inject.util.Modules;

import java.util.Properties;

@Slf4j
public class PropertiesLoaderListener extends BaseTestNGListener {

    public PropertiesLoaderListener() {
        super(Injector.create(Modules.combine(Modules.override(new ConfigurationModule())
                        .with(new Module() {
                            @Override
                            public void configure(Binder binder) {
                                setupReportPortalProperties(binder);
                            }
                        }),
                new ReportPortalClientModule(),
                new TestNGAgentModule()
        )));
    }

    private static void setupReportPortalProperties(Binder binder) {
        String testEnvironmentName = Constants.validateAndGetTestEnvironment();
        LOG.info("Environment set to: " + testEnvironmentName);
        if (!testEnvironmentName.equals(Constants.DEFAULT_ENV)) {
            String testType = Constants.validateAndGetTestType();
            String[] testTargets = Constants.validateAndGetTestTargets();

            PropertiesLoader propertiesLoader = PropertiesLoader.load();
            Properties overrides = new Properties();
            overrides.setProperty(ListenerProperty.ENABLE.getPropertyName(), "true");
            overrides.setProperty(ListenerProperty.LAUNCH_NAME.getPropertyName(), composeLaunchName(testType, testTargets));
            overrides.setProperty(ListenerProperty.LAUNCH_TAGS.getPropertyName(), composeLaunchTags(testEnvironmentName, testType, testTargets));
            propertiesLoader.overrideWith(overrides);
            binder.bind(PropertiesLoader.class).toInstance(propertiesLoader);
        }
    }

    private static String composeLaunchName(String testType, String[] testTargets) {
        String launchName = testType + "-Test";
        if (testTargets.length == 1) {
            return launchName + "-" + testTargets[0];
        } else {
            return launchName;
        }
    }

    private static String composeLaunchTags(String testEnvironmentName, String testType, String[] testTargets) {
        StringBuilder tags = new StringBuilder();
        tags.append(testEnvironmentName);
        tags.append(";");
        tags.append(testType);
        for (String testTarget : testTargets) {
            tags.append(";");
            tags.append(testTarget);
        }
        return tags.toString();
    }
}