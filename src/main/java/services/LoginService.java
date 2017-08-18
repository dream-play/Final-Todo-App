package services;

import database.Store;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

public class LoginService {
    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {

        Map<String, User> loginDetails = Store.getInstance().getUserNameDetails();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
//        System.out.println(userName + " " +  password);
        String loginMessage = null;

        if(loginDetails.containsKey(userName)) {
//            System.out.println("Username found with actual password " + loginDetails.get(userName).getPassword());
            if(loginDetails.get(userName).getPassword().equals(password)) {
//                System.out.println("password is right");
                String token = new BigInteger(130, new SecureRandom()).toString(32);
                response.addCookie(new Cookie("tokenId", token));
                response.sendRedirect("/test/home.html");
                Store.getInstance().addUser(loginDetails.get(userName), token);
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home");
//                requestDispatcher.include(request, response);
                loginMessage = "success";
            } else {
                loginMessage = "Wrong Password";
            }
        } else {
            loginMessage = "Entered Username does not Exist";
        }
        System.out.println(loginMessage);
        response.setHeader("Content-Type", "application/json");
        JSONObject jsonMessage = new JSONObject();
        try {
            jsonMessage.put("loginMessage", loginMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(jsonMessage);
        out.flush();
    }
}
