package com.zhtty.mock.box.utils;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author A8142
 */
@Slf4j
public class JsonUtils {

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectReader reader(TypeReference<?> typeReference) {
        return mapper.readerFor(typeReference);
    }

    public static ObjectReader reader(Class<?> clazz) {
        return mapper.readerFor(clazz);
    }

    public static ObjectReader reader() {
        return mapper.reader();
    }

    public static ObjectWriter writer() {
        return mapper.writer();
    }

    public static <T> T readValue(Class<T> clazz, String content) {
        try {
            return mapper.readerFor(clazz).readValue(content);
        } catch (Exception e) {
            AnyThrow.throwUnchecked(e);
        }
        return null;
    }

    public static <T> T readValue(Class<T> clazz, byte[] content) {
        try {
            return mapper.readerFor(clazz).readValue(content);
        } catch (Exception e) {
            AnyThrow.throwUnchecked(e);
        }
        return null;
    }

    public static <T> T readValue(TypeReference<T> typeReference, String content) {
        try {
            return mapper.readerFor(typeReference).readValue(content);
        } catch (Exception e) {
            AnyThrow.throwUnchecked(e);
        }
        return null;
    }

    public static <T> T readValue(TypeReference<T> typeReference, byte[] content) {
        try {
            return mapper.readerFor(typeReference).readValue(content);
        } catch (Exception e) {
            AnyThrow.throwUnchecked(e);
        }
        return null;
    }

    public static String writeValueAsString(Object o) {
        try {
            return mapper.writer().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            AnyThrow.throwUnchecked(e);
        }
        return StrUtil.EMPTY;
    }

    public static byte[] writeValueAsBytes(Object o) {
        if (o == null) {
            return null;
        }
        try {
            return mapper.writer().writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            AnyThrow.throwUnchecked(e);
        }
        return new byte[]{};
    }
}