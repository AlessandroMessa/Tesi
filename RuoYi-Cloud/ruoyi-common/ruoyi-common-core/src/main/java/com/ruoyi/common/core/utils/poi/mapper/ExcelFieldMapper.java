package com.ruoyi.common.core.utils.poi.mapper;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.annotation.Excels;
import com.ruoyi.common.core.annotation.Excel.Type;
import com.ruoyi.common.core.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelFieldMapper<T> {

    private final Class<T> clazz;
    private final Type type;
    private final String[] includeFields;
    private final String[] excludeFields;

    private List<Field> subFields;
    private Method subMethod;

    public ExcelFieldMapper(Class<T> clazz, Type type, String[] includeFields, String[] excludeFields) {
        this.clazz = clazz;
        this.type = type;
        this.includeFields = includeFields;
        this.excludeFields = excludeFields;
    }

    public List<Object[]> getMappedFields() {
        List<Object[]> fields = new ArrayList<>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz.getDeclaredFields()));

        for (Field field : tempFields) {
            // include only
            if (StringUtils.isNotEmpty(includeFields) &&
                    !(ArrayUtils.contains(includeFields, field.getName()) || field.isAnnotationPresent(Excels.class))) {
                continue;
            }

            // exclude only
            if (StringUtils.isNotEmpty(excludeFields) &&
                    ArrayUtils.contains(excludeFields, field.getName())) {
                continue;
            }

            addField(fields, field);
        }

        return fields.stream()
                .sorted(Comparator.comparing(objects -> ((Excel) objects[1]).sort()))
                .collect(Collectors.toList());
    }

    private void addField(List<Object[]> fields, Field field) {
        // Single annotation
        if (field.isAnnotationPresent(Excel.class)) {
            Excel attr = field.getAnnotation(Excel.class);
            if (attr != null && (attr.type() == Type.ALL || attr.type() == this.type)) {
                fields.add(new Object[]{field, attr});
            }

            if (Collection.class.isAssignableFrom(field.getType())) {
                this.subMethod = getSubMethod(field.getName(), clazz);
                ParameterizedType pt = (ParameterizedType) field.getGenericType();
                Class<?> subClass = (Class<?>) pt.getActualTypeArguments()[0];
                this.subFields = FieldUtils.getFieldsListWithAnnotation(subClass, Excel.class);
            }
        }

        // Multiple annotations
        if (field.isAnnotationPresent(Excels.class)) {
            Excels attrs = field.getAnnotation(Excels.class);
            for (Excel attr : attrs.value()) {
                if (attr != null && (attr.type() == Type.ALL || attr.type() == this.type)) {
                    fields.add(new Object[]{field, attr});
                }
            }
        }
    }

    private Method getSubMethod(String name, Class<?> pojoClass) {
        String getMethod = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            return pojoClass.getMethod(getMethod);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Field> getSubFields() {
        return subFields;
    }

    public Method getSubMethod() {
        return subMethod;
    }
}
