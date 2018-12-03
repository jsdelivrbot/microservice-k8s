package com.epam.testcommon.reportportal.utilities;

import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Listeners;

@Listeners(PropertiesLoaderListener.class)
public class TestBase extends AbstractTransactionalTestNGSpringContextTests {
}
