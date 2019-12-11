package chooseadventure.config;

import chooseadventure.exceptions.HttpException;
import chooseadventure.exceptions.ServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) // vis. RestErrorHandler
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<?> handle(Exception e, WebRequest request) {
        if(e instanceof ServerErrorException) {
            LOG.error("Hit Internal Server Error", e);
        }

        return ResponseEntity.status(((HttpException)e).getStatus()).body(Map.of("dialog", e.getMessage()));
    }
}
