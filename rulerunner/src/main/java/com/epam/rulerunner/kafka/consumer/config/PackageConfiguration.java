package com.epam.rulerunner.kafka.consumer.config;

import java.util.Set;

//@Getter
//@AllArgsConstructor
public class PackageConfiguration {
    private Set<String> packages;

    public PackageConfiguration(Set<String> packages) {
        this.packages = packages;
    }

    public Set<String> getPackages() {
        return packages;
    }

    public void setPackages(Set<String> packages) {
        this.packages = packages;
    }
}
