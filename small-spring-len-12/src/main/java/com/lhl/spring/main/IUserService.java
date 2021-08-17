package com.lhl.spring.main;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-12
 * @time 20:29
 * @describe:
 */
public interface IUserService {

    String queryUserInfo();

    String register(String userName);
}
