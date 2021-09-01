package com.lhl.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 21:54
 * @describe: 抽象Resource 接口
 * 主要用于处理资源加载流
 */
public interface Resource {

    /**
     * getInputStream
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
