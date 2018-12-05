package com.epam.event.kafka.consumer.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
public class EventScanner {

    public static void register(BeanDefinitionRegistry registry, Set<String> packageNames) {
        Assert.notNull(registry, "Registry must not be null");
        Assert.notNull(packageNames, "PackageNames must not be null");

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
            .genericBeanDefinition(PackageConfiguration.class)
            .addConstructorArgValue(packageNames)
            .getBeanDefinition();
        registry.registerBeanDefinition("packageConfiguration", beanDefinition);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    static class Registrar implements ImportBeanDefinitionRegistrar {

        @Override
        public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
            register(registry, getPackagesToScan(metadata));
        }

        private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
            AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableEpamKafkaConsumer.class.getName()));
            String[] basePackages = attributes.getStringArray("basePackages");
            Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
            Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(basePackages));
            for (Class<?> basePackageClass : basePackageClasses) {
                packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
            }
            if (packagesToScan.isEmpty()) {
                String packageName = ClassUtils.getPackageName(metadata.getClassName());
                Assert.state(!StringUtils.isEmpty(packageName), "@EnableEpamKafkaConsumer cannot be used with the default package");
                return Collections.singleton(packageName);
            }
            return packagesToScan;
        }
    }
}
