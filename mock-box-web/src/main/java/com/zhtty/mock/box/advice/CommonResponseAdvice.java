package com.zhtty.mock.box.advice;



import com.github.pagehelper.Page;
import com.zhtty.mock.box.context.OriginContext;
import com.zhtty.mock.box.dao.model.PageResponse;
import com.zhtty.mock.box.dao.model.Response;
import com.zhtty.mock.box.dao.model.WebConstants;
import com.zhtty.mock.box.page.PageUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author A8142
 */
@ControllerAdvice
@Order(0)
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !(returnType.getParameterType() == Response.class || HttpEntity.class.isAssignableFrom(returnType.getParameterType()));
    }

    @Override
    public Response<Object> beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                            ServerHttpRequest httpRequest, ServerHttpResponse httpResponse) {

        Page<Object> page = PageUtils.getPage();
        Response<Object> response;
        if (page != null) {
            PageResponse<Object> pageResponse = new PageResponse();
            pageResponse.setData(body);
            pageResponse.setPage(page.getPageNum());
            pageResponse.setPageSize(page.getPageSize());
            pageResponse.setPages(page.getPages());
            pageResponse.setTotal(page.getTotal());
            pageResponse.setOriginal(OriginContext.getOriginal());

            PageUtils.clearPage();

            response = Response.ofSuccess(pageResponse);
        } else {
            response = Response.ofSuccess(body);
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        String requestId = String.valueOf(requestAttributes.getAttribute(WebConstants.REQUEST_ID, RequestAttributes.SCOPE_REQUEST));
        response.setRequestId(requestId);
        return response;
    }

}