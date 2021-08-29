package com.lhl.spring.main;

import com.lhl.spring.stereotype.Component;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-29
 * @time 15:51
 * @describe:
 */
@Component("companyService")
public class CompanyService {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String get() {
        return "test company";
    }

}
