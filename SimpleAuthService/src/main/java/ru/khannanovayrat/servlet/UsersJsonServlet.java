package ru.khannanovayrat.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.khannanovayrat.config.JavaConfiguration;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ayrat on 02.11.2016.
 */
public class UsersJsonServlet extends HttpServlet{

    private static Logger log = Logger.getLogger(UsersJsonServlet.class.getName());

    private enum Request{
        USERS,
        USER_ID,
        USER_CARS,
        USER_ADD_CAR
    }

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        userService = (UserService) context.getBean("userService");
    }

    private class RestRequest{
        private Pattern regExAllPattern = Pattern.compile("/users");
        private Pattern regExIdPattern = Pattern.compile("/users\\?id=([0-9]+)");
        private Pattern reqExIdAuPattern = Pattern.compile("/users/([0-9]+)/autos");

        private int id;
        private Request requestType;

        public RestRequest(String pathInfo) throws ServletException{
            Matcher matcher;

            log.info("path info: " + pathInfo);
            matcher = regExIdPattern.matcher(pathInfo);
            if(matcher.find()){
                id = Integer.parseInt(matcher.group(1));
                log.info("found path with id = " + id);
                requestType = Request.USER_ID;
                return;
            }
            matcher = regExAllPattern.matcher(pathInfo);
            if(matcher.find()) {
                log.info("nothing found in path");
                requestType = Request.USERS;
                return;
            }

            throw new ServletException("Invalid URL");
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Request getRequestType() {
            return requestType;
        }

        public void setRequestType(Request requestType) {
            this.requestType = requestType;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        log.info("Parameter found id =" + );
//        ObjectMapper objectMapper = new ObjectMapper();
//        if(request.getParameter("id") != null){
//            int id = Integer.parseInt(request.getParameter("id"));
//            User user = userService.getUser(id);
//            response.getWriter().write(objectMapper.writeValueAsString(user));
//        }
        try {
            RestRequest restRequest = new RestRequest(request.getPathInfo() + "?" +
                    request.getQueryString());
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json");
            switch (restRequest.getRequestType()){
                case USERS:
                    List<User> users = userService.getAll();
                    String usersJson = mapper.writeValueAsString(users);
                    response.getWriter().write(usersJson);
                    break;
                case USER_ID:
                    User user = userService.getUser(restRequest.getId());
                    response.getWriter().write(mapper.writeValueAsString(user));
                    break;
            }
        }catch (ServletException e){
            e.printStackTrace();
        }
    }
}
