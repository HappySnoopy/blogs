/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.fileimport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author linjun
 */
public class FileImportServiceAsExcelTest {

    private FileImportServiceAsExcel<ExcelModel> service = new FileImportServiceAsExcel<>();

    @Before
    public void setUp() {

        this.service = new FileImportServiceAsExcel<>();

        List<String> headers = new ArrayList<>(2);
        headers.add("姓名");
        headers.add("年龄");
        headers.add("生日");

        List<List<String>> headerList = new ArrayList<>(1);
        headerList.add(headers);

        this.service.setHeaderList(headerList);

        List<String> cells = new ArrayList<>(2);
        cells.add("name");
        cells.add("age");
        cells.add("birthday");

        this.service.setCellList(cells);

        this.service.setClaz(ExcelModel.class);

    }

    /**
     * 校验：非excel格式
     */
    @Test
    public void test_Not_Excel() {

        byte[] excelFile = FileImportServiceAsExcelTest.readFile("text.txt");

        try {
            this.service.importFile(excelFile, "linjun");
        } catch (RuntimeException e) {
            Assert.assertEquals("请选择指定格式（后缀名为.xls或.xlsx）的Excel文件！",
                e.getMessage());
        }

    }

    /**
     * 校验：错误表头
     */
    @Test
    public void test_Wrong_Header() {
        byte[] excelFile = FileImportServiceAsExcelTest
            .readFile("test_wrong_header.xlsx");

        try {
            this.service.importFile(excelFile, "linjun");
        } catch (RuntimeException e) {
            Assert.assertEquals("表头取值错误！应为[姓名]，实为[姓名1]", e.getMessage());
        }

    }

    @Test
    public void test_blank() {
        byte[] excelFile = FileImportServiceAsExcelTest
            .readFile("test_blank_row.xlsx");

        try {
            List<ExcelModel> list = this.service
                .importFile(excelFile, "linjun");

            Assert.assertEquals(1, list.size());

            System.out.println(list);

            Assert.assertEquals("林俊", list.get(0).getName());
            Assert.assertEquals(new Integer(23), list.get(0).getAge());
            Assert.assertEquals("2016-01-04",
                new SimpleDateFormat("yyyy-MM-dd").format(list.get(0)
                    .getBirthday()));
            Assert.assertEquals("linjun", list.get(0).getSomeObject()
                .toString());
        } catch (RuntimeException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 测试正常数据
     */
    @Test
    public void test() {
        byte[] excelFile = FileImportServiceAsExcelTest.readFile("test.xlsx");

        try {
            List<ExcelModel> list = this.service
                .importFile(excelFile, "linjun");

            System.out.println(list);

            Assert.assertEquals("林俊", list.get(0).getName());
            Assert.assertEquals(new Integer(23), list.get(0).getAge());
            Assert.assertEquals("2016-01-04",
                new SimpleDateFormat("yyyy-MM-dd").format(list.get(0)
                    .getBirthday()));
            Assert.assertEquals("linjun", list.get(0).getSomeObject()
                .toString());
            Assert.assertEquals("陈晓诗", list.get(1).getName());
            Assert.assertEquals(new Integer(22), list.get(1).getAge());
            Assert.assertEquals("2016-01-04",
                new SimpleDateFormat("yyyy-MM-dd").format(list.get(1)
                    .getBirthday()));
            Assert.assertEquals("linjun", list.get(1).getSomeObject()
                .toString());
        } catch (RuntimeException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    private static byte[] readFile(String fileName) {
        byte[] excelFile = null;
        try (InputStream stream = FileImportServiceAsExcelTest.class
            .getResourceAsStream(fileName);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();) {

            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = stream.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }

            excelFile = baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelFile;
    }

}
