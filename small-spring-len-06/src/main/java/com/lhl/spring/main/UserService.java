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

    private String uid;

    private UserDao userDao;

    private String company;

    private String location;

    public UserService() {
    }

    public UserService(String uid, UserDao userDao, String company, String location) {
        this.uid = uid;
        this.userDao = userDao;
        this.company = company;
        this.location = location;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void queryUserInfo() {
        String userName = userDao.queryUserName(uid);
        System.out.println("查询用户信息：" + userName + " " + company + " " + location);
    }
}
