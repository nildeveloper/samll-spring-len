package com.lhl.spring.main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 15:38
 * @describe:
 */
public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("10001", "Tony");
        map.put("10002", "Jack");
        map.put("10003", "Mary");
    }

    public String queryUserName(String uid) {
        return map.get(uid);
    }
}
