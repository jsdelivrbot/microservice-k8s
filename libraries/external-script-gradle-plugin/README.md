# External script resolver plugin

Resolves a given dependency and extracts to the *\<projects build dir\>/scripts* folder

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
        externalScriptPluginVersion = '1.0.2-SNAPSHOT'
    }

    dependencies {
        (...)
        classpath "com.epam.gradle:external-script-gradle-plugin:$externalScriptPluginVersion"
    }
}
```
### Apply plugin
```
apply plugin: 'external_script'
```

### Resolving gradle script artifact
Resolves the given dependency and extracts the contents. (default location is the scripts folder under the root projects build directory.)

```
ext {
    commonScriptLocation = scriptExtension.resolveCommonScriptLocation("com.epam:ava-api-common:1.0.0-SNAPSHOT")
}

apply from: "$commonScriptLocation/deploy.gradle"
```

### Resolving contract artifact
Resolves the given dependency and extracts the contents. (default location is the contract/external folder under projects root directory.)
```
scriptExtension.resolveCommonContract("com.epam.contract.api:contract-commons:1.0.0-SNAPSHOT")
```