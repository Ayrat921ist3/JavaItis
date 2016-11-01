package ru.khannanovayrat.filter;

import ru.khannanovayrat.util.Verifier;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Ayrat on 26.10.2016.
 */
public class ListFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        for (Cookie cookie : ((HttpServletRequest)servletRequest).getCookies()){
            String cookieValue = cookie.getValue();
            if (cookie.getName().equals("token") && Verifier.verifyTokenValid(cookieValue)){
                System.out.println("Doing filter, \n" +
                        ".. Token found");
                servletRequest.setAttribute("user_token", cookieValue);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        System.out.println("Can't find user with this token.");
    }

    public void destroy() {

    }
}
