package com.model;

public class User {
    private int id;
    private String userid;
    private String pwd;
    private int state;
    private int priority;
    private String text;

    public User() {
    }

    public User(int id, String userid, String pwd, int state, int priority, String text) {
        this.id = id;
        this.userid = userid;
        this.pwd = pwd;
        this.state = state;
        this.priority = priority;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", state=" + state +
                ", priority=" + priority +
                ", text='" + text + '\'' +
                '}';
    }
}
