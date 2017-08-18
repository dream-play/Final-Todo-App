package controller;

import database.Store;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

@WebServlet(urlPatterns = {
        "/login" // POST
})
public class    LoginController extends HttpServlet{
    @Override
    protected void doPost( HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        LoginService.serviceMethod(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}
