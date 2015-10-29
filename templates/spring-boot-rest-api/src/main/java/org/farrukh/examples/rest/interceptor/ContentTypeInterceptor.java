package org.farrukh.examples.rest.interceptor;

import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Specialized handler interceptor for checking content type consistency.
 */
public class ContentTypeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (request.getContentType() == null) {
            throw new RuntimeException("A content type could not bee null");
        }
        if (!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new HttpMediaTypeNotSupportedException("A content type have to be application/json");
        }
        return false;
    }
}
