package com.lhl.spring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 22:13
 * @describe: FileSystemResource
 * 从系统文件获取资源
 */
public class FileSystemResource implements Resource {

    private final String path;

    private File file;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public String getPath() {
        return path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream fileInputStream = new FileInputStream(this.file);
        return fileInputStream;
    }
}
