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

public class RegisterService  {
    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {
        Store database = Store.getInstance();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String emailId = request.getParameter("emailId");
        String phone = request.getParameter("phone");
        String registerMessage = null;

        response.setHeader("Content-Type", "application/json");
        if(database.getUserNameDetails().containsKey(userName) ) {
            registerMessage = "Username already exists";
        }
        else {
            System.out.println("Registering " + userName + " with pw as " + password);
            User newUser = new User(userName, password, emailId, phone);
            String token = new BigInteger(130, new SecureRandom()).toString(32);
            response.addCookie(new Cookie("tokenId", token));
            registerMessage = "Registration Successful";
            database.addUser(newUser, token);
            response.sendRedirect(request.getContextPath() + "/home.html");
        }

//        System.out.println("Registration Started");
//        RequestDispatcher.forward(request, response);

        JSONObject jsonMessage = new JSONObject();
        try {
            jsonMessage.put("registerMessage", registerMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(jsonMessage);
        out.flush();
    }
}
