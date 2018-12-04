package com.epam.gradle.dependency

class Dependency {
    private final String group
    private final String name
    private final String version

    Dependency(String group, String name, String version) {
        this.group = group
        this.name = name
        this.version = version
    }

    String getGroup() {
        return group
    }

    String getName() {
        return name
    }

    String getVersion() {
        return version
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Dependency that = (Dependency) o

        if (group != that.group) return false
        if (name != that.name) return false
        if (version != that.version) return false

        return true
    }

    int hashCode() {
        int result
        result = (group != null ? group.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        return result
    }


    @Override
    public String toString() {
        return "Dependency{" +
                "group='" + group + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
