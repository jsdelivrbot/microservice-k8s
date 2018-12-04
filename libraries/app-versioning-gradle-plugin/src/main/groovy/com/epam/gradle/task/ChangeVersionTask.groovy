package com.epam.gradle.task

import com.epam.gradle.version.Version
import com.epam.gradle.version.VersionHandler
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

import static java.util.Objects.nonNull

class ChangeVersionTask extends DefaultTask {

    VersionHandler versionHandler

    @TaskAction
    void changeVersion() {
        def project = getProject()
        def buildFile = project.buildFile

        Version version = null

        boolean forced = false

        if (project.hasProperty("force")) {
            forced = true
        }

        if (project.hasProperty("newVersion")) {
            version = new Version(project.newVersion)
            versionHandler.setVersion(buildFile, version, forced)
        } else {
            version = setParts(project, buildFile, forced)
        }

        if (nonNull(version)) {
            project.version = version.fullVersion
        }
    }

    private Version setParts(Project project, File buildFile, boolean forced) {
        def version = versionHandler.getVersion(buildFile)

        if (project.hasProperty("increaseMajor")) {
            versionHandler.setVersion(
                    buildFile,
                    version.increaseMajor(1),
                    forced)
        }

        if (project.hasProperty("increaseMinor")) {
            versionHandler.setVersion(
                    buildFile,
                    version.increaseMinor(1),
                    forced)
        }

        if (project.hasProperty("increasePatch")) {
            versionHandler.setVersion(
                    buildFile,
                    version.increasePatch(1),
                    forced)
        }

        if (project.hasProperty("setToSnapshot")) {
            versionHandler.setVersion(
                    buildFile,
                    version.setToSnapshot().increasePatch(1),
                    forced)
        }

        if (project.hasProperty("setToRelease")) {
            versionHandler.setVersion(
                    buildFile,
                    version.setToRelease(),
                    forced)
        }

        return version
    }
}
