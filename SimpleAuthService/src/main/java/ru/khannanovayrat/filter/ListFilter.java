package ru.khannanovayrat.filter;

import ru.khannanovayrat.util.Verifier;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Ayrat on 26.10.2016.
 */
public class ListFilter implements Filter {

    private static Logger log = Logger.getLogger(ListFilter.class.getName());

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        for (Cookie cookie : ((HttpServletRequest)servletRequest).getCookies()){
            String cookieValue = cookie.getValue();
            Verifier verifier = new Verifier();
            if (cookie.getName().equals("token") && verifier.verifyTokenValid(cookieValue)){
                log.info("Doing filter, \n" +
                        ".. Token found");
                servletRequest.setAttribute("user_token", cookieValue);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        log.info("Can't find user with this token.");
        log.info("redirecting to login");
        ((HttpServletResponse)servletResponse).sendRedirect("/login");
    }

    public void destroy() {

    }
}
