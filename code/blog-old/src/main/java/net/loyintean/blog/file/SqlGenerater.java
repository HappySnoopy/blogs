package net.loyintean.blog.file;

import java.io.FileReader;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

public class SqlGenerater {


    public static void main(String[] args) throws IOException {

        generateByStream("C:\\Users\\LAP02F2170925\\Desktop\\design\\极速待修复数据.csv",
                "C:\\Users\\LAP02F2170925\\Desktop\\design\\[工单SQL]-[MySql]-[new-apply]-20190816-极速.sql",
                "update application_info set state =\"Z\", state_detail=\"Z_0\",update_time=now() where apply_id = {0}  and state = \"A\";\r\n");

        // cbm库的更新语句
        // "update tb_cbm_apply_link set status = \"FROZENED\", update_time =now() where apply_id={0} and status = \"APPLYING\";\r\n"
    }

    private static void generate(String from, String to, String format) throws IOException {
        List<String> in = Files.readAllLines(Paths.get(from));
        String newFileName = to;

        // 每1千条提交一次
        int commitCount = 1000;
        // 每5万条出一个新文件
        int renameCount = 50000;
        // 重命名计数器
        int nameCount = 0;
        // 总量计数器
        int i = 0;
        // 每条数据
        String data;
        // 拼接完之后的内容
        StringBuilder content = new StringBuilder();

        Path filePath = Paths.get(newFileName);
        for (Iterator<String> iterator = in.iterator(); iterator.hasNext(); ) {
            data = iterator.next();

            if (i % renameCount == 0) {
                newFileName = rename(to, ++nameCount);
                filePath = Paths.get(newFileName);
            }

            content.append(MessageFormat.format(format, data));


            if (i % commitCount == (commitCount - 1)) {
                content.append("COMMIT;\r\nSLEEP(1);\r\n");
            }

            System.out.println(content.toString());

            Files.write(filePath, content.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);


            i++;
            iterator.remove();
            content.delete(0, content.length());
        }

        Files.write(Paths.get(newFileName), "COMMIT;\r\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        System.out.println("向 " + to + " 中写入了 " + i + " 条SQL。");
    }

    private static String rename(String to, int i) {

        // 扩展名的位置
        int fileExtendsNameIndex = to.lastIndexOf(".");

        String newName = to.substring(0, fileExtendsNameIndex) + "第" + i + "批" + to.substring(fileExtendsNameIndex);

        return newName;
    }


    private static void generateByStream(String from, String to, String format) throws IOException {

        File fromFile = new File(from);
        if (!fromFile.exists()) {
            fromFile.createNewFile();
        }


        // 每1千条提交一次
        int commitCount = 1000;
        // 每5万条出一个新文件
        int renameCount = 50000;
        // 文件数量计数器
        int fileCount = 0;

        int totalCount = 0;

        StringBuilder content = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fromFile), 1024)) {

            // 一个文件中最多若干条
            for (int countInOneFile = 0; countInOneFile % renameCount == 0; countInOneFile++) {
                try (BufferedWriter fileWriter = generateWriter(to, fileCount++)) {
                    // 不停读数据，直到读完
                    while (-1 != fileReader.read()) {

                        // 拼接sql内容
                        content.append(MessageFormat.format(format, fileReader.readLine()));
                        // 总量达到一定程度时，写COMMIT和SLEEP
                        if (totalCount % commitCount == (commitCount - 1)) {
                            content.append("COMMIT;\r\nSLEEP(1);\r\n");
                        }
                        fileWriter.write(content.toString());
                        totalCount++;
                        content.delete(0, content.length());
                    }
                }
            }
        }
    }

    private static BufferedWriter generateWriter(String to, int nameCount) throws IOException {
        String fileName = rename(to, ++nameCount);
        File toFile = new File(fileName);
        if (!toFile.exists()) {
            toFile.createNewFile();
        }
        return new BufferedWriter(new FileWriter(toFile), 1024);
    }

}
