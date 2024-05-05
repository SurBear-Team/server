package com.surbear.common.authorization;

import com.surbear.JwtTokenProvider;
import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
public class AuthorizationArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authorization.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory)
            throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            throw new ProcessErrorCodeException(BasicErrorCode.AUTHORIZATION_HEADER_NULL);
        }

        if (!header.startsWith("Bearer ")) {
            throw new ProcessErrorCodeException(BasicErrorCode.AUTHENTICATION_FAILURE_ERROR);
        }

        String token = header.substring(BEARER_PREFIX_LEN);
        return Long.parseLong(jwtTokenProvider.getPayload(token));
    }

    public AuthorizationArgumentResolver(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private final static int BEARER_PREFIX_LEN = 7;
}
