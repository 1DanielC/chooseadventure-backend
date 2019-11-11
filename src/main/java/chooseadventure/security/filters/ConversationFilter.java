package chooseadventure.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class ConversationFilter implements Filter {

    private static final String CONVERSATION_HEADER = "CONVERSATION";

    @Autowired
    public ConversationFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        filterChain.doFilter(request, response);

        response.setHeader(CONVERSATION_HEADER, UUID.randomUUID().toString());
    }

}
