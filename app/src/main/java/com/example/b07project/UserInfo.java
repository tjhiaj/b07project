package com.example.b07project;

public class UserInfo {
    public enum RoleType {
        Admin,
        Student
    }

    private RoleType role = RoleType.Student;

    private static UserInfo instance = null;

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType roleType) {
        this.role = role;
    }

    public static UserInfo getInstance()
    {
        if(instance == null)
            instance = new UserInfo();
        return instance;
    }

}
