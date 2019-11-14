package chooseadventure.data.repository;


import chooseadventure.data.model.session.Session;

import javax.servlet.http.Cookie;

public interface RedisSessionRepository {
    void updateSession(String token, Session session);
    Session getSession(Cookie cookie);
    void destroySession(Cookie cookie);
}
