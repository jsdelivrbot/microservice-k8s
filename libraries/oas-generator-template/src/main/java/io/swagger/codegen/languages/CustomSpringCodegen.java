package io.swagger.codegen.languages;

import com.google.common.collect.Sets;
import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.SupportingFile;
import io.swagger.models.HttpMethod;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.nonNull;

public class CustomSpringCodegen extends SpringCodegen implements CodegenConfig {

    @Override
    public String getName() {
        return "io.swagger.codegen.languages.CustomSpringCodegen";
    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        Map<String, Object> operations = (Map<String, Object>) super.postProcessOperations(objs).get("operations");
        List<CodegenOperation> operation = (List<CodegenOperation>) operations.get("operation");
        operation.forEach(codegenOperation -> {
            if (HttpMethod.GET.toString().equals(codegenOperation.httpMethod)) {
                codegenOperation.hasConsumes = false;
                codegenOperation.consumes = Collections.emptyList();
            }
        });
        return objs;
    }

    @Override
    public void processOpts() {
        super.processOpts();

        String infoPackage = (String) additionalProperties.get("infoPackage");
        if (nonNull(infoPackage)) {
            addContractInfoSupportingFiles(infoPackage);

            String apiConfigPackage = (String) additionalProperties.get("apiConfigPackage");
            if (nonNull(apiConfigPackage)) {
                addSwaggerConfigSupportingFiles(apiConfigPackage);
            }
        }

        Boolean removeSpringSpecificFiles = (Boolean) additionalProperties.get("removeSpringBootTemplates");
        Set<String> specificMustacheFiles = getSpringBootTemplates();
        if (nonNull(removeSpringSpecificFiles) && removeSpringSpecificFiles) {
            Iterator<SupportingFile> supportingFileIterator = supportingFiles.iterator();
            while (supportingFileIterator.hasNext()) {
                SupportingFile supportingFile = supportingFileIterator.next();

                if (specificMustacheFiles.contains(supportingFile.templateFile)) {
                    supportingFileIterator.remove();
                }
            }
        }
    }

    @Override
    public Map<String, Object> postProcessModels(Map<String, Object> objs) {
        return super.postProcessModels(objs);
    }

    private Set<String> getSpringBootTemplates() {
        return Sets.newHashSet(
                "homeController.mustache",
                "swagger2SpringBoot.mustache",
                "RFC3339DateFormat.mustache",
                "application.mustache",
                "swaggerDocumentationConfig.mustache");
    }

    private void addSwaggerConfigSupportingFiles(String apiConfigPackage) {
        String apiConfigFolder = packageToFilePath(sourceFolder + "." + apiConfigPackage);

        supportingFiles.add(new SupportingFile("swaggerUiConfig.mustache", apiConfigFolder, "SwaggerUiConfig.java"));
    }

    private void addContractInfoSupportingFiles(String infoPackage) {
        String infoFolder = packageToFilePath(sourceFolder + "." + infoPackage);

        supportingFiles.add(new SupportingFile("apiInfo.mustache", infoFolder, "ApiInfo.java"));
        supportingFiles.add(new SupportingFile("apiContractController.mustache", infoFolder, "ApiContractController.java"));
    }

    private String packageToFilePath(String packageName) {
        return packageName.replace(".", java.io.File.separator);
    }
}
