package model;

public class User {
    private String userName;
    private String password;
    private String emailId;
    private String phone;

    public User(String userName, String password, String emailId, String phone) {
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
