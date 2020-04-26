/**
 * All Rights Reserved
 */
package net.loyintean.blog.repay;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.loyintean.blog.rest.client.RestClientFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author winters1224@163.com
 */
public class Calculator {

    public static void main(String[] args) {
        try {

            List<Result4Calculate> dtos = Calculator.readXls();

            System.out.println(dtos.size());

            List<Result4Repay> newXlsDtos = Calculator.calculae(dtos);

            Calculator.xlsDto2Excel(newXlsDtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Result4Repay> calculae(List<Result4Calculate> dtos) {
        RestClientFactory factory = new RestClientFactory();
        factory.setUserName("");
        factory.setPassWord("");
        ObjectMapper mapper = new ObjectMapper();

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        List<Future<Result4Repay>> futureList = new ArrayList<>(dtos.size());

//        Iterator<Result4Calculate> iterator = dtos.subList(0, 2).iterator();

        for (Iterator<Result4Calculate> iterator = dtos.iterator(); iterator
            .hasNext(); iterator.remove()) {

            Result4Calculate dto = iterator.next();

            System.out.println(dto);

            Future<Result4Repay> future = executorService.submit(() -> {

                String result = factory.newClient()
                    .setUrl(
                        "")
                    .addPathVariable("lendId",
                        Integer.toString(dto.getLendId()))
                    .addRequestParam("settleDate", "2017-10-09")
                    .responseAs(String.class).get();

                RestResult4Repay restResult;
                try {
                    restResult = mapper.readValue(result,
                        RestResult4Repay.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    restResult = null;
                }

                if (restResult == null || !restResult.isSuccess()) {
                    restResult = new RestResult4Repay();
                    restResult.setSuccess(true);
                    restResult.setData(new Result4Repay());
                }
                // 确保overdue中有lendId
                restResult.getData().getOverdue().setLendId(dto.getLendId());

                return restResult.getData();
            });
            futureList.add(future);
        }

        List<Result4Repay> newDtos = futureList.parallelStream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }).filter(r -> r != null).collect(Collectors.toList());

        return newDtos;
    }

    private static void xlsDto2Excel(List<Result4Repay> xls) throws Exception {
        // 获取总列数
        int CountColumnNum = 5;
        // 创建Excel文档
        HSSFWorkbook hwb = new HSSFWorkbook();
        Result4Repay xlsDto = null;
        // sheet 对应一个工作页
        HSSFSheet sheet = hwb.createSheet("pldrxkxxmb");
        HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
        HSSFCell[] firstcell = new HSSFCell[CountColumnNum];
        String[] names = new String[CountColumnNum];
        names[0] = "进件号";
        names[1] = "逾期总额";
        names[2] = "当期总额";
        names[3] = "提前还款总额";
        names[4] = "应还金额";
        for (int j = 0; j < CountColumnNum; j++) {
            firstcell[j] = firstrow.createCell(j);
            firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
        }

        Iterator<Result4Repay> iterator = xls.iterator();
        xls = null;
        for (int i = 0; iterator.hasNext(); i++, iterator.remove()) {

            System.out.println("xlsDto = " + xlsDto);
            // 创建一行
            HSSFRow row = sheet.createRow(i + 1);
            // 得到要插入的每一条记录
            xlsDto = iterator.next();
            for (int colu = 0; colu <= names.length; colu++) {
                // 在一行内循环
                Result4Calculate overdue = xlsDto.getOverdue();
                BigDecimal overdueValue = overdue.getRepayPrincipal()
                    .add(overdue.getRepayInterest())
                    .add(overdue.getRepayMgmtFee())
                    .add(overdue.getRepayOtherFees())
                    .add(overdue.getPenaltyInterest())
                    .add(overdue.getLiquidatedFee())
                    .subtract(overdue.getDeductAmount())
                    .subtract(overdue.getRepaidAmount());
                HSSFCell overdueCell = row.createCell(1);
                // 逾期总额，本金+利息+服务费+杂费+罚息+违约金-减免-已还
                overdueCell.setCellValue(overdueValue.toString());

                // 由于overdue中铁定有数据，因此可以直接用这个值
                HSSFCell li = row.createCell(0);
                li.setCellValue(overdue.getLendId());
                overdue = null;
                overdueCell = null;

                // 当期总额，本金+利息+服务费+杂费-减免-已还
                Result4Calculate current = xlsDto.getCurrent();
                BigDecimal currentValue = current.getRepayPrincipal()
                    .add(current.getRepayInterest())
                    .add(current.getRepayMgmtFee())
                    .add(current.getRepayOtherFees())
                    .subtract(current.getDeductAmount())
                    .subtract(current.getRepaidAmount());
                HSSFCell currentCell = row.createCell(2);
                currentCell.setCellValue(currentValue.toString());

                current = null;
                currentCell = null;

                // 提前总额，期初本金+违约金-返还-减免-已还
                Result4Calculate advance = xlsDto.getAdvance();
                BigDecimal advanceValue = advance.getOriginPrincipal()
                    .add(advance.getInAdvanceLiquidated())
                    .subtract(advance.getInAdvanceBonus())
                    .subtract(advance.getDeductAmount())
                    .subtract(advance.getRepaidAmount());
                HSSFCell advanceCell = row.createCell(3);
                advanceCell.setCellValue(advanceValue.toString());

                advance = null;
                advanceCell = null;
                // 总额
                HSSFCell totalCell = row.createCell(4);
                totalCell.setCellValue(overdueValue.add(currentValue)
                    .add(advanceValue).toString());


                totalCell = null;
            }
        }
        // 创建文件输出流，准备输出电子表格
        @SuppressWarnings("resource")
        OutputStream out = new FileOutputStream("E:/download/结清计算结果.xls");
        hwb.write(out);
        out.close();
        System.out.println("数据导出成功");
    }

    @SuppressWarnings("resource")
    private static List<Result4Calculate> readXls() throws IOException {
        InputStream is = new FileInputStream("E:/download/进件号.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Result4Calculate xlsDto = null;
        List<Result4Calculate> list = new ArrayList<>();
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
                xlsDto = new Result4Calculate();
                // 循环列Cell
                HSSFCell xh = hssfRow.getCell(0);
                if (xh == null) {
                    continue;
                }

                xlsDto.setLendId(Integer.parseInt(Calculator.getValue(xh)));

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
