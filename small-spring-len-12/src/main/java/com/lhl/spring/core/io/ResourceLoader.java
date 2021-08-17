package com.lhl.spring.core.io;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 23:00
 * @describe: 资源加载器
 * 按照资源加载的不同方式，资源加载器可以把这些方式集中到
 * 统一的类服务下进行处理，外部用户只需要传递资源地址即可，
 * 简化使用。
 */
public interface ResourceLoader {

    String CLASS_PATH_PREFIX = "classpath:";

    String HTTP_PREFIX = "http";

    /**
     * getResource
     * @param location
     * @return
     */
    Resource getResource(String location);
}
