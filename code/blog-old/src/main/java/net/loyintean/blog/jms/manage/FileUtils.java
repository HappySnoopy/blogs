/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.manage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Snoopy
 * @since 2017年11月1日
 */
public class FileUtils {

    public static void write(String msg) {

        Path path = Paths
            .get("D:\\Program Files\\apache-activemq-5.10.0\\data\\test.txt");

        try {
            Files.write(path, msg.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(msg, e);
        }
    }

}
