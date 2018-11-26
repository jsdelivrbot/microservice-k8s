package io.swagger.codegen.languages;

import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenParameter;
import io.swagger.codegen.CodegenType;

import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class ProtoSpringCodegen extends CustomSpringCodegen {

    @Override
    public String getName() {
        return "io.swagger.codegen.languages.ProtoSpringCodegen";
    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        Map<String, Object> operations = (Map<String, Object>) super.postProcessOperations(objs).get("operations");
        List<CodegenOperation> operation = (List<CodegenOperation>) operations.get("operation");
        operation.forEach(this::processOperation);
        return objs;
    }

    private void processOperation(CodegenOperation codegenOperation) {
        String returnType = codegenOperation.returnType;

        if (codegenOperation.isListContainer) {
            codegenOperation.isListContainer = false;
            codegenOperation.returnSimpleType = true;
            codegenOperation.returnContainer = null;

            codegenOperation.returnType += "Proto." + returnType + "s";
        } else {
            codegenOperation.returnType += "Proto." + returnType;
        }

        processBodyParameters(codegenOperation);
    }

    private void processBodyParameters(CodegenOperation codegenOperation) {
        List<CodegenParameter> bodyParams = codegenOperation.bodyParams;
        bodyParams.forEach(this::processBodyParam);
        CodegenParameter bodyParam = codegenOperation.bodyParam;
        if (nonNull(bodyParam)) {
            processBodyParam(bodyParam);
        }
    }

    private void processBodyParam(CodegenParameter codegenParameter) {
        String bodyParameterType = codegenParameter.dataType;

        if (codegenParameter.isListContainer) {
            codegenParameter.dataType += "Proto." + bodyParameterType + "s";
        } else {
            codegenParameter.dataType += "Proto." + bodyParameterType;
        }
    }
}
