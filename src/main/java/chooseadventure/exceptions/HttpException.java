package chooseadventure.exceptions;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {
    private HttpStatus httpStatus;

    public HttpException(HttpStatus status, String message) {
        super(message);
        httpStatus = status;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }

}
