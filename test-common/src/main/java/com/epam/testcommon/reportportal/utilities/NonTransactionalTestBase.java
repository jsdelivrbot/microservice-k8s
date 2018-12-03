package com.epam.testcommon.reportportal.utilities;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

@Listeners(PropertiesLoaderListener.class)
public class NonTransactionalTestBase extends AbstractTestNGSpringContextTests {
}
