package controller;

import database.Store;
import model.Todo;
import model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import services.HomeService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(urlPatterns = {
        "/home", //GET, POST
        "/addTask"
})
public class HomeController extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        HomeService.serviceGetMethod(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        HomeService.servicePostMethod(request, response);
    }



}
