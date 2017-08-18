package model;

import java.util.Date;

public class Todo {
    private String title;
    private String body;
    private String creator;
    private String assignee;
    private String status;
    private int todoId;
    private Date createdDate;
    private static int count = 0;

    public Todo(String title, String body, String creator) {
        this.title = title;
        this.body = body;
        this.creator = creator;
        this.assignee = null;
        this.todoId = count++;
        this.createdDate = new Date();
        this.status = "unassigned";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
