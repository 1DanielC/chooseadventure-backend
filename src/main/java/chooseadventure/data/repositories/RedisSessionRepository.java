package chooseadventure.data.repositories;


import chooseadventure.data.models.session.Session;

import javax.servlet.http.Cookie;

public interface RedisSessionRepository {
    String createSession();
    void updateSession(String token, Session session);
    Session getSession(Cookie cookie);
    void destroySession(Cookie cookie);
}
