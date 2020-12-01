/**
 * All Rights Reserved
 */
package net.loyintean.blog.bae.repay;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.loyintean.blog.rest.client.RestClientFactory;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * 解析Excel，发起还款。
 *
 * @author Snoopy
 */
public class Repayer {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final RestClientFactory factory = new RestClientFactory();
    static {
        Repayer.factory.setUserName("");
        Repayer.factory.setPassWord("!");
    }

    /**
     * @param args
     * @author Snoopy
     */
    public static void main(String[] args) {

        System.out.println(args[0]);

        // 定制任务
        Timer timer = new Timer(false);

        // 解析文件，获取数据
        List<RepayDto> repayList = Repayer.parstFromFile(args[0]);

        System.out.println(repayList.size());

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // 遍历数据，进行支付
                Repayer.doRepay(repayList);
            }
        };

        // 每六个小时跑一次 6*60*60*1000
        timer.scheduleAtFixedRate(task, 0, 6 * 60 * 60 * 1000);

    }

    /**
     * @param repayList
     * @author Snoopy
     */
    private static void doRepay(List<RepayDto> repayList) {

        Date today = new Date();
        repayList.parallelStream().filter(
            // 只还当天的
            dto -> DateUtils.truncatedCompareTo(today, dto.getBookDate(),
                Calendar.DAY_OF_MONTH) == 0)
            .map(dto -> {
                try {
                    return Repayer.OBJECT_MAPPER.writeValueAsString(dto);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            }).filter(json -> json != null).forEach(json -> {

                System.out.println(json);
                try {
                    Map<String, String> result = Repayer.factory.newClient()
                        .setUrl("").setBody(json).responseAs(HashMap.class)
                        .addHeader("content-type", "text/html; charset=UTF-8")
                        .post();
                    System.out.println(result);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            });
    }

    /**
     * 从文件中获取数据
     *
     * @return
     * @author Snoopy
     */
    private static List<RepayDto> parstFromFile(String path) {
        // 根文件是educationRepayList
        File dir = new File(path);

        List<RepayDto> list = new LinkedList<>();
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                list.addAll(Repayer.parseFromFile(f));
            }
        }

        return list;
    }

    /**
     * @param f
     * @return
     * @author Snoopy
     */
    private static List<RepayDto> parseFromFile(File f) {

        List<RepayDto> list = new LinkedList<>();
        try (InputStream fileInputStream = new FileInputStream(f)) {

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            for (int numSheet = 0; numSheet < workbook
                .getNumberOfSheets(); numSheet++) {
                XSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 1; rowNum <= hssfSheet
                    .getLastRowNum(); rowNum++) {
                    Row hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }

                    RepayDto dto = new RepayDto();
                    Cell cell = hssfRow.getCell(0);
                    // 确保只有两个cell
                    dto.setLrId(
                        Double.valueOf(cell.getNumericCellValue()).intValue());

                    cell = hssfRow.getCell(1);
                    dto.setBookDate(cell.getDateCellValue());

                    list.add(dto);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static class RepayDto {
        private int lrId;
        @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING,
                timezone = "GMT+08")
        private Date bookDate;

        /**
         * @return the {@link #bookDate}
         */
        public Date getBookDate() {
            return this.bookDate;
        }

        /**
         * @param bookDate
         *        the {@link #bookDate} to set
         */
        public void setBookDate(Date bookDate) {
            this.bookDate = bookDate;
        }

        /**
         * @return the {@link #lrId}
         */
        @SuppressWarnings("unused")
        public int getLrId() {
            return this.lrId;
        }

        /**
         * @param lrId
         *        the {@link #lrId} to set
         */
        public void setLrId(int lrId) {
            this.lrId = lrId;
        }

    }
}
