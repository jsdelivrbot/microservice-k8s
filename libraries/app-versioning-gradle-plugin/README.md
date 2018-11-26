# App versioning gradle plugin

Adds task to change the root projects version in the build.gradle file

## Howto
- Build: ./gradlew build
- Local Install: ./gradlew publishToMavenLocal
- Artifactory publish: ./gradlew artifactoryPublish


### Usage
Add to buildScript eg:
```
buildscript {
    repositories {
        ...
    }
    
    ext {
        appVersioningPluginVersion = '1.0.0-SNAPSHOT'
    }

    dependencies {
        (...)
        classpath "com.epam.gradle:app-versioning-gradle-plugin:$appVersioningPluginVersion"
    }
}
```
### Apply plugin
```
apply plugin: 'app_versioning'
```

### Changing version number

There is one main task that handles the version changes:
```
./gradlew changeVersion [ properties ]
->
./gradlew changeVersion -PsetToRelease
./gradlew changeVersion -PnewVersion=1.0.12-SNAPSHOT -Pforce
```

#### Properties

-P{property-name[={property-value}]}

* **force**                 - forces the change (in case smaller version is given or same release version is set)
* **newVersion string**     - applying the given new version (format: \d+\.\d+\.\d+-(SNAPSHOT|RELEASE) eg. 1.0.0-SNAPSHOT) other properties are ignored except **force**

* **increaseMajor**         - increases the patch version part (1.0.0-SNAPSHOT -> 2.0.0-SNAPSHOT)
* **increaseMinor**         - increases the patch version part (1.0.0-SNAPSHOT -> 1.1.0-SNAPSHOT)
* **increasePatch**         - increases the patch version part (1.0.0-SNAPSHOT -> 1.0.1-SNAPSHOT)
* **setToSnapshot**         - sets the version as SNAPSHOT and increases the patch version part (1.0.0-RELEASE -> 1.0.1-SNAPSHOT)
* **setToRelease**          - sets the version as RELEASE (1.0.0-SNAPSHOT -> 1.0.0-RELEASE)