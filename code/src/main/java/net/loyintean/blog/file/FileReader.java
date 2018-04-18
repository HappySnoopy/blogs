/**
 * All Rights Reserved
 */
package net.loyintean.blog.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author linjun
 * @since 2017年11月28日
 */
public class FileReader {

    /**
     * @author linjun
     * @since 2017年11月28日
     * @param args
     */
    public static void main(String[] args) {
        Path path = Paths.get(
            "D:\\apache-tomcat-9.0.0.M22\\wtpwebapps\\thread_finance\\WEB-INF\\lib");

        File file = path.toFile();

        File[] jars = file.listFiles();

        Stream.of(jars).forEach(jar -> System.out.println(jar.getName()));
    }
}
