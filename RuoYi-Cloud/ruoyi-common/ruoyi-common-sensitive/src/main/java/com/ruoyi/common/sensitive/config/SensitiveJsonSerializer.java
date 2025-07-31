package com.ruoyi.common.sensitive.config;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.sensitive.annotation.Sensitive;
import com.ruoyi.common.sensitive.enums.DesensitizedType;
import com.ruoyi.common.sensitive.utils.annotation.SensitiveAnnotationResolver;

/**
 * 数据脱敏序列化过滤
 *
 * @author ruoyi
 */
public class SensitiveJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {
    private DesensitizedType desensitizedType;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (desensitization() && desensitizedType != null) {
            gen.writeString(desensitizedType.desensitizer().apply(value));
        } else {
            gen.writeString(value);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {
        this.desensitizedType = SensitiveAnnotationResolver.resolve(property);
        if (this.desensitizedType != null && Objects.equals(String.class, property.getType().getRawClass())) {
            return this;
        }
        return prov.findValueSerializer(property.getType(), property);
    }

    private boolean desensitization() {
        try {
            Long userId = SecurityContextHolder.getUserId();
            return !UserConstants.isAdmin(userId);
        } catch (Exception e) {
            return true;
        }
    }
}

