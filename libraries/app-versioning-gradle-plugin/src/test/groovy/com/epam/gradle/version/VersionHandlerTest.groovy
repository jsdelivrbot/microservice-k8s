package com.epam.gradle.version

import org.codehaus.groovy.runtime.ResourceGroovyMethods
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

import static java.nio.charset.StandardCharsets.UTF_8

@Test(enabled = true)
class VersionHandlerTest {

    private static final VERSION_TEXT = '1.0.0-SNAPSHOT'
    private static final VERSION_DEFINITION = "version '$VERSION_TEXT'"

    private File tempFile
    private VersionHandler versionHandler

    @BeforeTest
    void createTempFile() {
        versionHandler = new VersionHandler()
        tempFile = File.createTempFile("version", "test")
        tempFile.deleteOnExit()
    }

    @BeforeMethod
    void writeVersionDefinition() {
        ResourceGroovyMethods.write(tempFile, VERSION_DEFINITION, UTF_8.toString())
    }

    @Test
    void extractsVersion() {
        def version = versionHandler.getVersion(tempFile)

        Assert.assertEquals(version, new Version(VERSION_TEXT))
    }

    @Test
    void setsVersion() {
        def expectedVersion = '1.1.0-SNAPSHOT'
        def version = new Version(expectedVersion)
        def force = false

        versionHandler.setVersion(tempFile, version, force)

        Assert.assertEquals(versionHandler.getVersion(tempFile).fullVersion, expectedVersion)
    }

    @Test(expectedExceptions = RuntimeException.class)
    void validationFailsWhenOldVersionEqualsNewVersionAndModifierIsRelease() {
        ResourceGroovyMethods.write(tempFile, "version '1.0.0-RELEASE'", UTF_8.toString())
        def version = new Version('1.0.0-RELEASE')
        def force = false

        versionHandler.setVersion(tempFile, version, force)
    }

    @Test
    void setsVersionWithForcedWhenOldVersionEqualsNewVersionAndModifierIsRelease() {
        ResourceGroovyMethods.write(tempFile, "version '1.0.0-RELEASE'", UTF_8.toString())
        def expectedVersion = '1.0.0-RELEASE'
        def version = new Version(expectedVersion)
        def force = true

        versionHandler.setVersion(tempFile, version, force)

        Assert.assertEquals(versionHandler.getVersion(tempFile).fullVersion, expectedVersion)
    }

    @Test
    void setsVersionWhenOldVersionEqualsNewVersionAndModifierIsSnapshot() {
        def version = new Version(VERSION_TEXT)
        def force = false

        versionHandler.setVersion(tempFile, version, force)

        Assert.assertEquals(versionHandler.getVersion(tempFile).fullVersion, VERSION_TEXT)
    }

    @Test(expectedExceptions = RuntimeException.class)
    void validationFailsWhenOldVersionLessThanNewVersion() {
        def version = new Version('0.1.1-SNAPSHOT')
        def force = false

        versionHandler.setVersion(tempFile, version, force)
    }

    @Test
    void setsVersionWithForcedWhenOldVersionLessThanNewVersion() {
        def expectedVersion = '0.1.1-SNAPSHOT'
        def version = new Version(expectedVersion)
        def force = true

        versionHandler.setVersion(tempFile, version, force)

        Assert.assertEquals(versionHandler.getVersion(tempFile).fullVersion, expectedVersion)
    }

    @Test(expectedExceptions = RuntimeException.class)
    void failsWhenNoVersionFound() {
        ResourceGroovyMethods.write(tempFile, "some text", UTF_8.toString())
        def version = new Version('0.1.1-SNAPSHOT')
        def force = false

        versionHandler.setVersion(tempFile, version, force)
    }
}
