package com.epam.gradle

import com.epam.gradle.dependency.Dependency
import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils
import org.gradle.api.Project

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

import static com.epam.gradle.ExternalScriptHelper.CONFIGURATION_NAME
import static com.epam.gradle.ExternalScriptHelper.META_INF_EXCLUDE
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING

class ExternalScriptExtension {
    private final Project project
    private final List<Dependency> dependencies = new ArrayList<>()

    String excludeString = META_INF_EXCLUDE
    String scriptRelativeLocation = "scripts"
    String contractRelativeLocation = "contract/external"

    ExternalScriptExtension(Project project) {
        this.project = project
    }

    String resolveCommonScriptLocation(String dependencyName) {
        def locationPath = createLocationPath(project.buildDir, scriptRelativeLocation)
        return resolveAndExtractDependency(dependencyName, locationPath, scriptRelativeLocation)
    }

    String resolveCommonContract(String dependencyName) {
        def locationPath = createLocationPath(project.rootDir, contractRelativeLocation)
        cleanDirectory(locationPath)
        return resolveAndExtractDependency(dependencyName, locationPath, contractRelativeLocation)
    }

    List<String> getDependencies() {
        return new ArrayList<>(dependencies)
    }

    private cleanDirectory(String locationPath) {
        FileUtils.cleanDirectory(new File(locationPath))
        println "Directory cleaned: $locationPath"
    }

    private String resolveAndExtractDependency(String dependencyName, String location, String name) {
        def config = project.configurations.create(CONFIGURATION_NAME + name)
        config.dependencies.add(project.dependencies.create(dependencyName))

        def file = config.singleFile
        def resolvedLocation = file.absolutePath
        println "Resolved dependency: $dependencyName at: $resolvedLocation"

        extractJar(file, location)

        project.configurations.remove(config)
        addDependency(dependencyName)

        return location
    }

    private boolean addDependency(String dependencyName) {
        def parts = dependencyName.split(":")
        dependencies.add(new Dependency(parts[0], parts[1], parts[2]))
    }

    private String createLocationPath(File rootLocation, String relativeLocation) {
        def absoluteRootLocation = rootLocation.absolutePath
        Path locationPath = Paths.get(absoluteRootLocation, relativeLocation)
        String location = locationPath.toString()

        println "Path created: $location"

        File scriptFolder = new File(location)
        scriptFolder.mkdirs()

        return location
    }


    private void extractJar(File jarFile, String location) {

        def zipFile = new ZipFile(jarFile)

        for (ZipEntry entry : zipFile.entries()) {
            def fileName = entry.name
            def targetPath = Paths.get(location, fileName)
            def targetFile = new File(targetPath.toUri())

            if (!isExcluded(fileName)) {
                if (entry.isDirectory()) {
                    targetFile.mkdirs()
                } else {
                    if (isWritable(targetFile)) {
                        Files.copy(zipFile.getInputStream(entry), targetPath, REPLACE_EXISTING)
                    }else {
                        println "File $targetPath is used by another process, skipping."
                    }

                }
            }
        }
    }

    private boolean isWritable(File targetFile) {
        if (targetFile.exists()) {
            if (targetFile.canWrite()) {
                return true
            } else {
                return false
            }
        }

        return true
    }

    private boolean isExcluded(String fileName) {
        return StringUtils.contains(fileName, excludeString)
    }
}


