package com.lhl.spring.main;

import com.lhl.spring.beans.factory.annotation.Autowired;
import com.lhl.spring.beans.factory.annotation.Value;
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

    @Value("${token}")
    private String token;

    @Autowired
    CompanyDao companyDao;

    public String get() {
        String s = companyDao.get("1002");
        return "company=" + s + " token=" + token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }
}
