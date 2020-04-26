package net.loyintean.blog.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileCopy {

    public static void main(String[] args) throws IOException {

        FileUtils.copyDirectory(new File("E:\\360Downloads"), new File("E:\\BaiduNetdiskDownload"));

    }
}
