package database;

import model.Todo;
import model.User;

import java.util.*;

public class Store{
    private static Store instance = null;

    private Map<String, User> SessionIdUser = null;
    private List<Todo> todos = null;
//    private List<User> users = null;
    private Map<String, User> userNameDetails = null;
    private Map<Date,Todo > updatedTodos;

    private Store() {
        this.SessionIdUser = new HashMap<String, User>();
        this.todos = new ArrayList<Todo>();
//        this.users = new ArrayList<User>();
        this.userNameDetails = new HashMap<String, User>();
        this.updatedTodos = new HashMap<Date, Todo>();
    }

    public Map<String, User> getUserNameDetails() {
        return userNameDetails;
    }

    public Map<Date, Todo> getUpdatedTodos() {
        return updatedTodos;
    }

    public static Store getInstance() {

        if(instance == null) {
            instance = new Store();
        }

        return instance;

    }

    public Map<String, User> getSessionIdUser() {
        return SessionIdUser;
    }

    public void setSessionIdUser(HashMap<String, User> sessionIdUser) {
        SessionIdUser = sessionIdUser;
    }

    public void addTodo(Todo todo) {
        this.todos.add(todo);
    }

    public Todo getTodo(int todoId) {
        for(int i = 0; i < todos.size(); i++) {
            if(todos.get(i).getTodoId() == todoId) {
                return todos.get(i);
            }
        }
        return null;
    }

    public void addUser(User user, String token) {
//        this.users.add(user);
        userNameDetails.put(user.getUserName(), user);
//        System.out.println("Store key " + token);
        SessionIdUser.put(token, user);
    }

    public List<Todo> getTodos() {
        return todos;
    }

//    public List<User> getUsers() {
//        return users;
//    }
    public void remove(Todo todo) {
        todos.remove(todo);
    }
    public void updateStatus(Todo todo) {
        if(todo.getStatus().equals("unassigned")) {
            todo.setStatus("assigned");
        } else if(todo.getStatus().equals("assigned")) {
            todo.setStatus("completed");
        }
    }
    public void updateTodo(Todo todo) {
        updatedTodos.put(new Date(), todo);
    }

    public boolean checkOKChange(Date date, int todoId) {
        for (Map.Entry<Date, Todo > entry : updatedTodos.entrySet())
        {
            if(entry.getKey().after(date) && entry.getValue().getTodoId() == todoId) {
                return false;
            }
        }
        return true;
    }

    public List<Todo> getForAUser(Date date){
        List<Todo> list = new ArrayList<Todo>();
        for (Map.Entry<Date, Todo > entry : updatedTodos.entrySet())
        {
            if(entry.getKey().after(date))
                list.add(entry.getValue());
        }
        return list;
    }
}