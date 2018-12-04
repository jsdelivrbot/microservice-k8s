package com.epam.gradle

import com.epam.gradle.task.ChangeVersionTask
import com.epam.gradle.version.VersionHandler
import org.gradle.api.Plugin
import org.gradle.api.Project

import static com.epam.gradle.VersionHandlerHelper.GROUP

class VersionHandlerPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def rootProject = project.rootProject

        rootProject.tasks.create('changeVersion', ChangeVersionTask, { task ->
            task.group = GROUP
            task.versionHandler = new VersionHandler()
        })
    }
}
