package com.zhtty.mock.box.advice;



import cn.hutool.core.util.StrUtil;

import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.model.Response;
import com.zhtty.mock.box.model.WebConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author A8142
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionAdvice {

    /**
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Response resolveException(Throwable ex) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        String requestId = String.valueOf(requestAttributes.getAttribute(WebConstants.REQUEST_ID, RequestAttributes.SCOPE_REQUEST));
        log.error("request id: {}, message: {}", requestId, ex.getMessage(), ex);

        Response response = Response.ofFail("SYSTEM_ERROR: " + ex.getMessage());
        response.setRequestId(requestId);
        return response;
    }

    /**
     * @param ex
     */
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    @ResponseBody
    public Response resolveCommonException(Exception ex) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        String requestId = String.valueOf(requestAttributes.getAttribute(WebConstants.REQUEST_ID, RequestAttributes.SCOPE_REQUEST));
        if (StrUtil.isBlank(ex.getMessage())) {
            log.error("request id: {}, message: {}", requestId, ex.getMessage(), ex);
        }
        Response response = Response.ofFail(ex.getMessage());
        response.setRequestId(requestId);
        return response;
    }

    /**
     * @param ex
     */
    @ExceptionHandler({BizException.class})
    @ResponseBody
    public Response<Object> resolveBizException(BizException ex) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        String requestId = String.valueOf(requestAttributes.getAttribute(WebConstants.REQUEST_ID, RequestAttributes.SCOPE_REQUEST));
        String message = ex.getMessage();
        if (ex.getData() != null) {
            message = message + ":" + ex.getData();
        }
        Response<Object> response = Response.ofFail(message);
        if (ex.getCode() != null) {
            response.setCode(ex.getCode());
        }
        response.setData(ex.getData());
        response.setRequestId(requestId);
        return response;
    }

}