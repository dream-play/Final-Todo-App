package services;

import database.Store;
import model.Todo;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateTaskService {
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
        System.out.println("getting user from session id ");
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

    public static void serviceMethod(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {
        if(checkLogin(request)) {
            response.setHeader("Content-Type", "application/json");
            User user = getUserFromSessionId(request);
            int todoId = Integer.parseInt(request.getParameter("todoId"));
//          System.out.println("todoId is " + todoId);
            String status = request.getParameter("status");
            Todo todo = Store.getInstance().getTodo(todoId);
            String message = "";
            if(todo == null) {
                message = "Todo Has already been deleted";
                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                response.getWriter().print(message);
                response.getWriter().flush();
            } else {
                String actualStatus = todo.getStatus();
                if(actualStatus.equals(status)) {
                    if (todo.getStatus().equals("unassigned")) {
                        todo.setStatus("assigned");
                        todo.setAssignee(user.getUserName());
                    } else if (todo.getStatus().equals("assigned")) {
                        todo.setStatus("completed");
                    } else {
                        System.out.println("deleting todoID" + todo.getTodoId());
                        todo.setStatus("deleted");
//                        Store.getInstance().remove(todo);
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                    message = "Current Status of this todo id " + todo.getStatus();
                    response.getWriter().print(message);
                    response.getWriter().flush();
                }
            }

        } else {
            response.sendRedirect("/test");
        }
    }
}
