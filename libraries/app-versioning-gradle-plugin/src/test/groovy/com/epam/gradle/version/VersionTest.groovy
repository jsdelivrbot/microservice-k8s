package com.epam.gradle.version

import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@Test(enabled = true)
class VersionTest {
    private static final FIRST_VERSION = '1.0.0-SNAPSHOT'
    private Version version

    @BeforeMethod
    void setUp() {
        version = Version.firstVersion()
    }

    @Test
    void firstVersionIsCorrect() {
        def firstVersion = Version.firstVersion()

        Assert.assertEquals(firstVersion.fullVersion, FIRST_VERSION)
    }

    @Test
    void changesMajor() {
        Version expectedVersion = new Version('2.0.0-SNAPSHOT')

        Assert.assertEquals(version.increaseMajor(1), expectedVersion)
    }

    @Test
    void changesMinor() {
        Version expectedVersion = new Version('1.1.0-SNAPSHOT')

        Assert.assertEquals(version.increaseMinor(1), expectedVersion)
    }

    @Test
    void changesPatch() {
        Version expectedVersion = new Version('1.0.1-SNAPSHOT')

        Assert.assertEquals(version.increasePatch(1), expectedVersion)
    }

    @Test
    void setsToSnapshot() {
        version = new Version('1.0.0-RELEASE')
        Version expectedVersion = new Version('1.0.0-SNAPSHOT')

        Assert.assertEquals(version.setToSnapshot(), expectedVersion)
    }

    @Test
    void setsToRelease() {
        Version expectedVersion = new Version('1.0.0-RELEASE')

        Assert.assertEquals(version.setToRelease(), expectedVersion)
    }

    @Test
    void comparesModifierCorrectly() {
        Version compare = new Version('1.0.0-RELEASE')

        Assert.assertTrue(version < compare)
    }

    @Test
    void comparesNumberCorrectly() {
        Version compare = new Version('1.1.0-SNAPSHOT')

        Assert.assertTrue(version < compare)
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void throwsExceptionWhenInvalidModifier() {
        new Version('1.0.0-SNAP')
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void throwsExceptionWhenMissingModifier() {
        new Version('1.0.0')
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void throwsExceptionWhenInvalidVersionNumber() {
        new Version('1.00-RELEASE')
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void throwsExceptionWhenInvalidNumber() {
        new Version('1.five.0-RELEASE')
    }
}
