package controller;

import database.Store;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;
import services.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

@WebServlet(urlPatterns = {
        "/register" // POST
        })
public class RegisterController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        RegisterService.serviceMethod(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
