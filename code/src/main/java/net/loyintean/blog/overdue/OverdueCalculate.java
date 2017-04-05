/**
 *
 * All Rights Reserved
 */
package net.loyintean.blog.overdue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.loyintean.blog.rest.client.RestClientFactory;

/**
 * @author winters1224@163.com
 */
public class OverdueCalculate {

    public static void main(String[] args) {
        try {

            List<XlsDto> dtos = OverdueCalculate.readXls();

            System.out.println(dtos.size());

            List<XlsDto> newXlsDtos = OverdueCalculate.calculae(dtos);

            OverdueCalculate.xlsDto2Excel(newXlsDtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<XlsDto> calculae(List<XlsDto> dtos) {
        RestClientFactory factory = new RestClientFactory();
        factory.setUserName("10020865");
        factory.setPassWord("");
        ObjectMapper mapper = new ObjectMapper();


        List<XlsDto> newDtos =
            dtos.parallelStream().map(dto -> {

                String result = factory.newClient()
                    .setUrl(
                        "")
                    .addPathVariable("lendId", dto.getLendId())
                    .addRequestParam("settleDate", "2017-06-01")
                    .responseAs(String.class).get();

                RestResult4OverdueCalculate restResult;
                try {
                    restResult = mapper.readValue(result,
                        RestResult4OverdueCalculate.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    restResult = null;
                }

                System.out.println(result);
                return restResult;
            }).filter(result -> result != null && result.isSuccess())
                .map(result -> result.getData())
                .collect(Collectors.toList());

        return newDtos;
    }

    private static void xlsDto2Excel(List<XlsDto> xls) throws Exception {
        // 获取总列数
        int CountColumnNum = 9;
        // 创建Excel文档
        HSSFWorkbook hwb = new HSSFWorkbook();
        XlsDto xlsDto = null;
        // sheet 对应一个工作页
        HSSFSheet sheet = hwb.createSheet("pldrxkxxmb");
        HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
        HSSFCell[] firstcell = new HSSFCell[CountColumnNum];
        String[] names = new String[CountColumnNum];
        names[0] = "进件号";
        names[1] = "应还本金";
        names[2] = "应还利息";
        names[3] = "应还服务费";
        names[4] = "应还杂费";
        names[5] = "应还罚息";
        names[6] = "应还违约金";
        names[7] = "账户余额";
        names[8] = "减免金额";
        for (int j = 0; j < CountColumnNum; j++) {
            firstcell[j] = firstrow.createCell(j);
            firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
        }
        for (int i = 0; i < xls.size(); i++) {

            System.out.println("第 " + i + " 行数据开始！");
            // 创建一行
            HSSFRow row = sheet.createRow(i + 1);
            // 得到要插入的每一条记录
            xlsDto = xls.get(i);
            for (int colu = 0; colu <= names.length; colu++) {
                // 在一行内循环
                HSSFCell li = row.createCell(0);
                li.setCellValue(xlsDto.getLendId());
                HSSFCell rp = row.createCell(1);
                rp.setCellValue(xlsDto.getRepayPrincipal().toString());
                HSSFCell ri = row.createCell(2);
                ri.setCellValue(xlsDto.getRepayInterest().toString());
                HSSFCell rm = row.createCell(3);
                rm.setCellValue(xlsDto.getRepayMgmtFee().toString());
                HSSFCell ro = row.createCell(4);
                ro.setCellValue(xlsDto.getRepayOtherFees().toString());
                HSSFCell pi = row.createCell(5);
                pi.setCellValue(xlsDto.getPenaltyInterest().toString());
                HSSFCell lf = row.createCell(6);
                lf.setCellValue(xlsDto.getLiquidatedFee().toString());
                HSSFCell b = row.createCell(7);
                b.setCellValue(xlsDto.getBalance().toString());
                HSSFCell da = row.createCell(8);
                da.setCellValue(xlsDto.getDeductAmount().toString());
            }
        }
        // 创建文件输出流，准备输出电子表格
        @SuppressWarnings("resource")
        OutputStream out = new FileOutputStream("E:/download/计算结果.xls");
        hwb.write(out);
        out.close();
        System.out.println("数据库导出成功");
    }

    @SuppressWarnings("resource")
    private static List<XlsDto> readXls() throws IOException {
        InputStream is = new FileInputStream("E:/download/进件号.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        XlsDto xlsDto = null;
        List<XlsDto> list = new ArrayList<>();
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
                xlsDto = new XlsDto();
                // 循环列Cell
                // 0学号 1姓名 2学院 3课程名 4 成绩
                // for (int cellNum = 0; cellNum <=4; cellNum++) {
                HSSFCell xh = hssfRow.getCell(0);
                if (xh == null) {
                    continue;
                }

                xlsDto.setLendId(OverdueCalculate.getValue(xh));

                list.add(xlsDto);
            }
        }
        return list;
    }

    /**
     * 得到Excel表中的值
     *
     * @param hssfCell
     *        Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String
                .valueOf(new Double(hssfCell.getNumericCellValue()).intValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
