package chooseadventure.security;

import chooseadventure.data.models.session.Session;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class SessionAuthentication extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = 4L;

    public SessionAuthentication(String sessionToken, Object principal) {
        super(principal, sessionToken, AuthorityUtils.NO_AUTHORITIES);
    }

    public Session getSession() {
        return (Session)getPrincipal();
    }

    public String getSessionToken() {
        return (String)getCredentials();
    }
}
