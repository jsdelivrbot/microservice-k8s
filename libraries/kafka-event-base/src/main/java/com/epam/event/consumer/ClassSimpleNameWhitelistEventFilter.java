package com.epam.event.consumer;

import java.util.Set;
import java.util.stream.Collectors;

public class ClassSimpleNameWhitelistEventFilter extends WhitelistEventFilter {

    public ClassSimpleNameWhitelistEventFilter(Set<Class> whitelist) {
        super(convertClassesToString(whitelist));
    }

    private static Set<String> convertClassesToString(Set<Class> classes) {
        return classes.stream().map(aClass -> aClass.getSimpleName()).collect(Collectors.toSet());
    }
}
