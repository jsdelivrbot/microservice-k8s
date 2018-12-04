package com.epam.gradle.version

class Version implements Comparable<Version> {

    public static final String RELEASE = 'RELEASE'
    public static final String SNAPSHOT = 'SNAPSHOT'

    private static final int LESS = -1
    private static final int MORE = 1
    private static final int EQUAL = 0

    private int major
    private int minor
    private int patch
    private String mod

    static Version firstVersion() {
        return new Version("1.0.0-SNAPSHOT")
    }

    static Builder builder() {
        return new Builder()
    }

    Version(String versionString) {
        initVersion(versionString)
    }

    String getFullVersion() {
        return "$major.$minor.$patch-$mod"
    }

    Version setMajor(int value) {
        major = value
        return this
    }

    Version seteMinor(int value) {
        minor = value
        return this
    }

    Version setPatch(int value) {
        patch = value
        return this
    }

    Version increaseMajor(int value) {
        major += value
        return this
    }

    Version increaseMinor(int value) {
        minor += value
        return this
    }

    Version increasePatch(int value) {
        patch += value
        return this
    }

    Version setToRelease() {
        mod = RELEASE
        return this
    }

    Version setToSnapshot() {
        mod = SNAPSHOT
        return this
    }


    int getMajor() {
        return major
    }

    int getMinor() {
        return minor
    }

    int getPatch() {
        return patch
    }

    String getMod() {
        return mod
    }

    private void initVersion(String versionString) {
        def modVersion = versionString.split("-")

        if (modVersion.length != 2) {
            throw new IllegalArgumentException(
                    "Version string $versionString is not valid (format: major.minor.patch-MOD eg: 1.1.0-RELEASE)")
        }

        def fullVersion = modVersion[0]
        mod = modVersion[1]

        if (!(mod.equals(RELEASE) || mod.equals(SNAPSHOT))) {
            throw new IllegalArgumentException(
                    "Version modifier ($mod) is not valid (RELEASE or SNAPSHOT)")
        }

        def fullVersionParts = fullVersion.split("\\.")

        if (fullVersionParts.length != 3) {
            throw new IllegalArgumentException(
                    "Version string $versionString is not valid (format: major.minor.patch-MOD eg: 1.1.0-RELEASE)")
        }

        major = fullVersionParts[0].toInteger()
        minor = fullVersionParts[1].toInteger()
        patch = fullVersionParts[2].toInteger()
    }


    @Override
    String toString() {
        return getFullVersion()
    }

    @Override
    int compareTo(Version otherVersion) {
        def otherMajor = otherVersion.getMajor()
        if (otherMajor > major) {
            return LESS
        } else if (otherMajor < major) {
            return MORE
        }

        def otherMinor = otherVersion.getMinor()
        if (otherMinor > minor) {
            return LESS
        } else if (otherMinor < minor) {
            return MORE
        }

        def otherPatch = otherVersion.getPatch()
        if (otherPatch > patch) {
            return LESS
        } else if (otherPatch < patch) {
            return MORE
        }

        def otherMod = otherVersion.getMod()
        if (otherMod.equals(RELEASE) && mod.equals(SNAPSHOT)) {
            return LESS
        } else if (otherMod.equals(SNAPSHOT) && mod.equals(RELEASE)) {
            return MORE
        }

        return EQUAL
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Version version = (Version) o

        if (major != version.major) return false
        if (minor != version.minor) return false
        if (patch != version.patch) return false
        if (mod != version.mod) return false

        return true
    }

    int hashCode() {
        int result
        result = major
        result = 31 * result + minor
        result = 31 * result + patch
        result = 31 * result + (mod != null ? mod.hashCode() : 0)
        return result
    }

    static class Builder {
        private int major
        private int minor
        private int patch
        private String mod

        Builder setMajor(int major) {
            this.major = major
            return this
        }

        Builder setMinor(int minor) {
            this.minor = minor
            return this
        }

        Builder setPatch(int patch) {
            this.patch = patch
            return this
        }

        Builder setMod(String mod) {
            this.mod = mod
            return this
        }

        Builder setToRelease() {
            mod = RELEASE
            return this
        }

        Builder setToSnapshot() {
            mod = SNAPSHOT
            return this
        }

        Version build() {
            return new Version("$major.$minor.$patch-$mod")
        }
    }
}
