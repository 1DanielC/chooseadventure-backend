package chooseadventure.data.repositories.impl;

import chooseadventure.data.models.session.Session;
import chooseadventure.data.repositories.RedisSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisSessionRepositoryImpl implements RedisSessionRepository {

    private static final int TIMEOUT = 60;
    private static final TimeUnit INTERVAL = TimeUnit.MINUTES;

    private RedisTemplate<String, Session> redisTemplate;

    @Autowired
    public RedisSessionRepositoryImpl(RedisTemplate<String, Session> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Nullable
    public String createSession() {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, new Session(), TIMEOUT, INTERVAL);
        return token;
    }

    @Override
    public void updateSession(String token, Session session) {
        redisTemplate.opsForValue().set(token, session, TIMEOUT, INTERVAL);
    }

    @Override
    @Nullable
    public Session getSession(Cookie cookie) {
        Session session = redisTemplate.opsForValue().get(cookie.getValue());
        if(session == null) {
            return null;
        }

        //reset expiration
        redisTemplate.opsForValue().set(cookie.getValue(), session, TIMEOUT, INTERVAL);

        return session;
    }

    @Override
    public void destroySession(Cookie cookie) {
        redisTemplate.delete(cookie.getValue());
    }

}
