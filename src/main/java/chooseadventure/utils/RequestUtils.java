package chooseadventure.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class RequestUtils {

    public static final String SESSION_COOKIE = "session";

    public static Optional<Cookie> getSessionCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(SESSION_COOKIE))
                .findFirst();
    }

    public static Cookie generateCookie() {
        return new Cookie(SESSION_COOKIE, UUID.randomUUID().toString());
    }

}