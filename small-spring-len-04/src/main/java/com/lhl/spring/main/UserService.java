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

    public UserService() {
    }

    public UserService(String uid, UserDao userDao) {
        this.uid = uid;
        this.userDao = userDao;
    }

    public void queryUserInfo() {
        String userName = userDao.queryUserName(uid);
        System.out.println("查询用户信息：" + userName);
    }
}
