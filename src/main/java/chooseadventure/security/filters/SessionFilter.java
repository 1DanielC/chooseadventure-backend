package chooseadventure.security.filters;

import chooseadventure.data.model.session.Session;
import chooseadventure.data.repository.RedisSessionRepository;
import chooseadventure.security.SessionAuthentication;
import chooseadventure.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class SessionFilter implements Filter {

    private RedisSessionRepository redisSessionRepository;

    @Autowired
    public SessionFilter(RedisSessionRepository redisSessionRepository) {
        this.redisSessionRepository = redisSessionRepository;
    }

    private static final Logger LOG = LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Optional<Cookie> sessionCookie = RequestUtils.getSessionCookie(request);

        sessionCookie.ifPresentOrElse(c -> {
            Session session = redisSessionRepository.getSession(c);
            SecurityContextHolder.getContext().setAuthentication(new SessionAuthentication(c.getValue(), session));
            c.setMaxAge(-1);
            response.addCookie(c);
        }, () -> {
            Cookie newCookie = new Cookie(RequestUtils.SESSION_COOKIE, UUID.randomUUID().toString());
            SecurityContextHolder.getContext().setAuthentication(new SessionAuthentication(newCookie.getValue(), new Session()));
            newCookie.setMaxAge(-1);
            response.addCookie(newCookie);
        });

        filterChain.doFilter(request, response);
    }

}
