package ru.khannanovayrat.filter;

import ru.khannanovayrat.util.CookieHelper;
import ru.khannanovayrat.util.Token;
import ru.khannanovayrat.util.Verifier;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Ayrat on 01.11.2016.
 */
public class LoginFilter implements Filter {

    private static Logger log = Logger.getLogger(LoginFilter.class.getName());

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        CookieHelper cookieHelper = new CookieHelper((HttpServletRequest)req);
        Cookie tokenCookie = cookieHelper.getCookie("token");
        if(tokenCookie != null &&
                Verifier.verifyTokenValid(tokenCookie.getValue())){
            log.info("doing login filter");
            ((HttpServletResponse)resp).sendRedirect("/list");
        }else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
