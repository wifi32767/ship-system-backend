package com.wifi32767.app.customizer;

import com.wifi32767.interfaces.common.Permission;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Component
public class PermissionAnnotationCustomizer implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        // 读取自定义注解
        Permission perm = handlerMethod.getMethodAnnotation(Permission.class);

        if (perm != null) {
            // 追加到描述尾部
            String desc = operation.getDescription() == null ? "" : operation.getDescription();
            operation.setDescription(desc +
                    "\n\n🔐 **权限要求**: " + perm.value());
        }

        return operation;
    }
}