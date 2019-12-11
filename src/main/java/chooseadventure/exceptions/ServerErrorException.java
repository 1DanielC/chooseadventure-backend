package chooseadventure.exceptions;

import org.springframework.http.HttpStatus;

public class ServerErrorException extends HttpException {

    public ServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
