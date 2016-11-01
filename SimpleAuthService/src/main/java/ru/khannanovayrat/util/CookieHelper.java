package ru.khannanovayrat.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ayrat on 01.11.2016.
 */
public class CookieHelper
{
    private List<Cookie> cookies;

    public CookieHelper(HttpServletRequest request) {
        this.cookies = Arrays.asList(request.getCookies());
    }

    public Cookie getCookie(String name){
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
}
