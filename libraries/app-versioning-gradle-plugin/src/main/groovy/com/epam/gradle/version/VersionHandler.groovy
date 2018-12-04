package com.epam.gradle.version

import org.codehaus.groovy.runtime.ResourceGroovyMethods

import java.util.regex.Matcher

import static java.nio.charset.StandardCharsets.UTF_8

class VersionHandler {

    public static final String VERSION_PATTERN = /(?m)version '(\d+\.\d+\.\d+-(SNAPSHOT|RELEASE))'/

    Version getVersion(File file) {
        def text = getText(file)
        return getVersionFromText(text)
    }

    void setVersion(File file, Version newVersion, boolean isForced) {
        def text = getText(file)
        def oldVersion = getVersionFromText(text)

        def forcedMsg = ''

        if (!isForced) {
            validateChange(oldVersion, newVersion)
        } else {
            forcedMsg = '- change is forced -'
        }

        def content = text.replace("version '$oldVersion'", "version '$newVersion'")

        ResourceGroovyMethods.write(file, content, UTF_8.toString())

        println "Modified newVersion $forcedMsg [ newVersion: $newVersion, oldVersion: $oldVersion, file: $file.absolutePath ]"
    }

    private String getText(File file) {
        return file.getText(UTF_8.toString())
    }

    private Version getVersionFromText(String text) {
        Matcher matcher = text =~ VERSION_PATTERN

        if (matcher) {
            return new Version(matcher.group(1))
        } else {
            throw new RuntimeException("Could not extract version number")
        }
    }

    private void validateChange(Version oldVersion, Version newVersion) {
        if (oldVersion.equals(newVersion) && oldVersion.getMod().equals(Version.RELEASE) && newVersion.getMod().equals(Version.RELEASE)) {
            throw new RuntimeException("Cannot assign the same RELEASE version ($newVersion) to a new artifact")
        }

        if (oldVersion > newVersion) {
            throw new RuntimeException("New version ($newVersion) is smaller than the old version ($oldVersion), aborting")
        }
    }
}
