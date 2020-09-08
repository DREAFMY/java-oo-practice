package com.twu.user.base;

import com.twu.options.AbstractBaseOptionImpl;

public class AbstractUser extends AbstractBaseOptionImpl {
    protected static final String ADMIN = "admin";
    protected static final String USER = "user";
    
    private String name;
    private String pwd;
    private String role;
    
    public AbstractUser(String name, String pwd, String role) {
        this.name = name;
        this.pwd = pwd;
        this.role = role;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }

    @Override
    public String toString() {
        return  "{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
