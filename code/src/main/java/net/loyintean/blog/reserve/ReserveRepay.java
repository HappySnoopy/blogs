/**
 * All Rights Reserved
 */
package net.loyintean.blog.reserve;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.loyintean.blog.rest.client.RestClientFactory;

/**
 * @author winters1224@163.com
 */
public class ReserveRepay {

    public static void main(String[] args) {
        try {

            List<Integer> dtos = ReserveRepay.readXls();

            System.out.println(dtos.size());

            ReserveRepay.reserveRepay(dtos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reserveRepay(List<Integer> dtos) {
        System.out.println("=============");

        RestClientFactory factory = new RestClientFactory();
        factory.setUserName("");
        factory.setPassWord("!");

        dtos.parallelStream().forEach(recordId -> {

            System.out.println(recordId);
            String result = factory.newClient().setUrl(
                "")
                .addHeader("content-type", "text/html")
                .setBody(null)
                .responseAs(String.class).post();

            System.out.println(result);
        });

    }

    @SuppressWarnings("resource")
    private static List<Integer> readXls() throws Exception {
        try {

            InputStream is = new FileInputStream("E:/download/进件号.xls");
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            List<Integer> list = new ArrayList<>();
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook
                .getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 1; rowNum <= hssfSheet
                    .getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    // 循环列Cell
                    // 0学号 1姓名 2学院 3课程名 4 成绩
                    // for (int cellNum = 0; cellNum <=4; cellNum++) {
                    HSSFCell xh = hssfRow.getCell(0);
                    if (xh == null) {
                        continue;
                    }

                    Double value = new Double(xh.getNumericCellValue());
                    list.add(value.intValue());
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
