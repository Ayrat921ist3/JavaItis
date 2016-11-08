package ru.khannanovayrat.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.khannanovayrat.config.JavaConfiguration;
import ru.khannanovayrat.models.Car;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.service.CarService;
import ru.khannanovayrat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
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
        USER_CARS
    }

    private UserService userService;
    private CarService carService;

    @Override
    public void init() throws ServletException {
        super.init();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        userService = (UserService) context.getBean("userService");
        carService = (CarService) context.getBean("carService");
    }

    private class RestRequest{
//        private Pattern regExAllPattern = Pattern.compile("");
        private Pattern regExIdPattern = Pattern.compile("\\?id=([0-9]+)");
        private Pattern regExIdAuPattern = Pattern.compile("/([0-9]+)/autos");

        private int id;
        private Request requestType;

        public RestRequest(String pathInfo) throws ServletException{
            Matcher matcher;

            log.info("path info: " + pathInfo);
            if(pathInfo == null) {
                log.info("nothing found in path");
                requestType = Request.USERS;
                return;
            }
            matcher = regExIdPattern.matcher(pathInfo);
            if(matcher.find()){
                id = Integer.parseInt(matcher.group(1));
                log.info("found path with id = " + id);
                requestType = Request.USER_ID;
                return;
            }
            matcher = regExIdAuPattern.matcher(pathInfo);
            if(matcher.find()){
                id = Integer.parseInt(matcher.group(1));
                log.info("found autos for user with id = " + id);
                requestType = Request.USER_CARS;
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
            String path = request.getPathInfo();
            if(request.getQueryString() != null){
                path += "?" + request.getQueryString();
            }
            RestRequest restRequest = new RestRequest(path);
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
                case USER_CARS:
                    List<Car> userCars = carService.getAll(restRequest.getId());
                    response.getWriter().write(mapper.writeValueAsString(userCars));
                    break;
            }
        }catch (ServletException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String path = request.getPathInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        if(request.getQueryString() != null){
            path += "?" + request.getQueryString();
        }
        RestRequest restRequest = new RestRequest(path);
        switch (restRequest.getRequestType()){
            case USER_CARS:
//                log.info("posting user car owner_id = " + request.get("owner_id"));
                StringBuffer jb = new StringBuffer();
                String line = null;
                try {
                    BufferedReader reader = request.getReader();
                    while ((line = reader.readLine()) != null)
                        jb.append(line);
                } catch (Exception e) { /*report an error*/ }
                try {
                    Car car = objectMapper.readValue(jb.toString(), Car.class);
                    carService.addCar(car);
                    log.info("parsed json = " + car);
                }catch (Exception e){
                    throw new IllegalArgumentException("can't parse given json");
                }
                log.info(jb.toString());
                break;
        }
    }
}
