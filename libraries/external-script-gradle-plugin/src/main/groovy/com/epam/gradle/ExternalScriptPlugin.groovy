package com.epam.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

import static ExternalScriptHelper.EXTENSION_NAME

class ExternalScriptPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        def rootProject = project.rootProject

        project.extensions.create(
                EXTENSION_NAME,
                ExternalScriptExtension,
                rootProject)
    }
}
