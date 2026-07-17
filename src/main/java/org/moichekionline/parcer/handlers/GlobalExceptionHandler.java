package org.moichekionline.parcer.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Map;

@Slf4j
@RestControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<Map<String, Object>> handleExternalApi(
            HttpStatusCodeException exception
    ) {
        log.error(
                "Внешний сервис ответил ошибкой: status={}, body={}",
                exception.getStatusCode(),
                exception.getResponseBodyAsString(),
                exception
        );

        return ResponseEntity.status(502).body(Map.of(
                "error", "Ошибка внешнего сервиса",
                "externalStatus", exception.getStatusCode().value()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleUnexpected(
            Exception exception
    ) {
        log.error("Ошибка при обработке запроса", exception);

        return ResponseEntity.internalServerError().body(Map.of(
                "error", "Внутренняя ошибка сервиса"
        ));
    }
}
