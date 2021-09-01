package com.lhl.spring.main;

import com.lhl.spring.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-31
 * @time 20:09
 * @describe:
 */
@Component
public class CompanyDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("1001", "1001");
        map.put("1002", "1002");
        map.put("1003", "1003");
    }

    public String get(String id) {
        return map.get(id);
    }
}
