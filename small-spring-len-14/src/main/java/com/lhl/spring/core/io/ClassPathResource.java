package com.lhl.spring.core.io;

import cn.hutool.core.lang.Assert;
import com.lhl.spring.util.ClassUtils;

import java.io.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 21:56
 * @describe: ClassPathResource
 * 从classpath获取资源
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "file path can not is null");
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    public String getPath() {
        return path;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // 通过 ClassLoader 读取 ClassPath 下的文件信息
        InputStream inputStream = classLoader.getResourceAsStream(this.path);
        if (inputStream == null) {
            throw new FileNotFoundException(path + " not found!");
        }
        return inputStream;
    }
}
