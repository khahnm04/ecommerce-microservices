package com.ecommerce_microservices.user_service.configuration;

import com.ecommerce_microservices.user_service.exception.ErrorCode;
import com.ecommerce_microservices.user_service.exception.ErrorResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;
import org.jspecify.annotations.NonNull;
import tools.jackson.databind.json.JsonMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final JsonMapper jsonMapper;

    @Override
    public void handle(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.FORBIDDEN;
        response.setStatus(errorCode.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorResponse errorResponse = ErrorResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .error(errorCode.getHttpStatus().getReasonPhrase())
            .path(request.getRequestURI())
            .timestamp(System.currentTimeMillis())
            .build();

        response.getWriter().write(jsonMapper.writeValueAsString(errorResponse));
        response.flushBuffer();
    }

}
