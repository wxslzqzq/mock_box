package com.zhtty.mock.box.advice;


import cn.hutool.core.util.ReflectUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zhtty.mock.box.context.OriginContext;
import com.zhtty.mock.box.model.WebConstants;
import com.zhtty.mock.box.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

/**
 * @author A8142
 */
@Slf4j
@ControllerAdvice
@Order(1)
public class TraceAdvice implements ResponseBodyAdvice<Object>, RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Object requestId = requestAttributes.getAttribute(WebConstants.REQUEST_ID, RequestAttributes.SCOPE_REQUEST);

        OriginContext.setOriginal(ReflectUtil.getFieldValue(body, "original"));

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            ObjectWriter writer = JsonUtils.writer();
            log.info("[Request], request id: {}, path: {}, body: {}", requestId, request.getRequestURI(), writer.writeValueAsString(body));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Object requestId = requestAttributes.getAttribute(WebConstants.REQUEST_ID, RequestAttributes.SCOPE_REQUEST);
        try {
            log.info("[Response], request id: {}, body: {}", requestId, JsonUtils.writer().writeValueAsString(body));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return body;
    }
}