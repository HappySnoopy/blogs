package net.loyintean.blog.file;

import java.io.FileReader;
import java.io.*;
import java.text.MessageFormat;

public class SqlGeneraterByIn {

    private static final int FILE_SIZE = 300000;
    private static final int TOTAL_COUNT_YRDAS = 395440;
    private static final int TOTAL_COUNT_JYAS = 6439;
    private static final int TOTAL_COUNT_GJJAS = 581740;
    private static int fileCount = 0;

    public static void main(String[] args) throws IOException {

        generateByStream("C:\\Users\\LAP02F2170925\\Desktop\\design\\极速待修复数据.csv",
                "C:\\Users\\LAP02F2170925\\Desktop\\design\\sql\\[工单SQL]-[MySql]-[cbm]-20190816-极速.sql",
                "update tb_cbm_apply_link set status = \"FROZENED\", update_time =now() where apply_id in ({0}) and status = \"APPLYING\";\r\n");

        // cbm库的更新语句
        // "update tb_cbm_apply_link set status = \"FROZENED\", update_time =now() where apply_id={0} and status = \"APPLYING\";\r\n"
    }


    private static void generateByStream(String from, String to, String format) throws IOException {
        File fromFile = new File(from);

        StringBuilder sql = new StringBuilder(100);
        StringBuilder inClause = new StringBuilder(20000);
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fromFile), 1024)) {
            int currentCount;
            int totalCount = 0;
            do {
                // 读取一个in子句，并记录总数
                currentCount = readInClause(inClause, fileReader);
                totalCount += currentCount;
                System.out.println("当前读取到数据总量:" + totalCount);

                // 拼接一个sql
                sql.append(MessageFormat.format(format, inClause.toString())).append("select sleep(1);\r\n");
                inClause.delete(0, sql.length());

                // 把sql写入文件中
                write(sql.toString(), to, totalCount % FILE_SIZE == 0);

                // 清掉本次读取的数据
                sql.delete(0, sql.length());
            } while (currentCount > 1);
        }
    }

    private static void write(String sql, String to, boolean newFile) throws IOException {
        File toFile;
        if (newFile) {
            toFile = new File(rename(to, fileCount++));
        } else {
            toFile = new File(rename(to, fileCount));
        }

        if (!toFile.exists()) {
            toFile.createNewFile();
        }

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(toFile, true), 1024)) {
            fileWriter.append(sql);
        }
    }

    private static int readInClause(StringBuilder inClause, BufferedReader fileReader) throws IOException {
        // 每1000条组装一个in语句

        int count = 0;
        String line;
        do {
            line = fileReader.readLine();
            if (line != null) {
                inClause.append(line).append(',');
                count++;
            }
        } while (line != null && count < 1000);
        // 删除最后一个逗号
        if (inClause.lastIndexOf(",") > 0) {
            inClause.delete(inClause.lastIndexOf(","), inClause.length());
        }
        return count;
    }

    private static String rename(String to, int i) {

        // 扩展名的位置
        int fileExtendsNameIndex = to.lastIndexOf(".");

        String newName = to.substring(0, fileExtendsNameIndex) + "第" + i + "批" + to.substring(fileExtendsNameIndex);

        return newName;
    }


}
