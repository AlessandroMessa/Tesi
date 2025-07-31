package com.ruoyi.common.sensitive.utils.annotation;

import com.ruoyi.common.sensitive.enums.DesensitizedType;
import com.ruoyi.common.sensitive.annotation.Sensitive;

import com.fasterxml.jackson.databind.BeanProperty;

public class SensitiveAnnotationResolver {
    public static DesensitizedType resolve(BeanProperty property) {
        Sensitive annotation = property.getAnnotation(Sensitive.class);
        if (annotation == null) {
            annotation = property.getContextAnnotation(Sensitive.class);
        }
        return annotation != null ? annotation.desensitizedType() : null;
    }
}
