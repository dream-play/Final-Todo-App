package services;

import database.Store;
import model.Todo;
import model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeService {
    private static boolean checkLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String tokenId = null;
        for(Cookie cookie: cookies) {
            if("tokenId".equals(cookie.getName())) {
                tokenId = cookie.getValue();
            }
        }
        if(tokenId == null) {
            return false;
        } else {
            return true;
        }
    }

    private static User getUserFromSessionId(HttpServletRequest request) {
        Map<String, User> sessionIdUser = Store.getInstance().getSessionIdUser();
        Cookie[] cookies = request.getCookies();
        String tokenId = null;
        for(Cookie cookie: cookies) {
            if("tokenId".equals(cookie.getName())) {
                tokenId = cookie.getValue();
            }
        }
        System.out.println("printing session id " + tokenId);
        return sessionIdUser.get(tokenId);
    }

    private static JSONObject jsonifyTodo(Todo todo) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("title", todo.getTitle());
        json.put("body", todo.getBody());
        json.put("createdDate", todo.getCreatedDate());
        json.put("creator", todo.getCreator());
        json.put("assignee", todo.getAssignee());
        json.put("todoId", todo.getTodoId());
        json.put("status", todo.getStatus());
        return json;

    }
    public static void servicePostMethod(HttpServletRequest request,
                                     HttpServletResponse response)
            throws ServletException, IOException {
        if(checkLogin(request)) {
            System.out.println("User is logged in thus Home post started");
            String title = request.getParameter("title");
            String body = request.getParameter("body");
            System.out.println("title = " + title + " body = " + body);
            String creator = getUserFromSessionId(request).getUserName();
            System.out.println("creator is " + creator);
            Todo newTodo = new Todo(title, body, creator);
            Store.getInstance().addTodo(newTodo);
            Store.getInstance().updateTodo(newTodo);
            response.sendRedirect("/test/home.html");
        } else {
            response.sendRedirect("/test");
        }
    }

    public static void serviceGetMethod(HttpServletRequest request,
                                         HttpServletResponse response)
            throws ServletException, IOException {
        if(checkLogin(request)) {

            response.setHeader("Content-Type", "application/json");

            String date = request.getParameter("date");
            List<Todo> todos = new ArrayList<Todo>();

            if(date.equals("undefined") || date.equals("null")) {
                // first time
                todos = Store.getInstance().getTodos();
            } else {
                DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                Date fdate = null;
                try {
                    fdate = format.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                todos = Store.getInstance().getForAUser(fdate);
            }

            JSONArray todoList = new JSONArray();
            for(Todo todo: Store.getInstance().getTodos()) {
                try {
                    todoList.put(jsonifyTodo(todo));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            JSONObject todoListObject = new JSONObject();
            try {
                todoListObject.put("todoList", todoList);
                todoListObject.put("date",new Date().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            PrintWriter writer = response.getWriter();
            writer.print(todoListObject);
            writer.flush();
        } else {
            response.sendRedirect("/test");
        }
    }
}
