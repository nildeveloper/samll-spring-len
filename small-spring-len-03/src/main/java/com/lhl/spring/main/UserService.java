package com.lhl.spring.main;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:15
 * @describe:
 */
public class UserService {

    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
        System.out.println("new instance userService");
    }

    public void queryUserInfo() {
        System.out.println("Hello World!");
    }

    public String getUserById(int id) {
        return "userId=" + id + " username=" + name;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
