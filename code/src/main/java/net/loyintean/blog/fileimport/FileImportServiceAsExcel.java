/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.fileimport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.loyintean.blog.log.LF;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 导入excel的服务框架
 * <p>
 * 使用标准化的org.apache.poi.ss.usermodel.*接口，兼容2003和2007格式。
 *
 * @author linjun
 * @param <T>
 */
class FileImportServiceAsExcel<T> implements FileImportService<T> {
    private static final Logger LOGGER = LoggerFactory
        .getLogger(FileImportServiceAsExcel.class);

    /**
     * 表头的映射列表。要求excel中表头全部是文本格式。
     * <p>
     * 本质上是一个二维数据。<br>
     * 第一维下标对应行号，第二位下标对应列号。<br>
     */
    private List<List<String>> headerList;

    /**
     * 表内数据的映射列表
     * <p>
     * 列表下标对应每一行中单元格的下标。<br>
     * 如果列表数据为null或空字符串，则跳过当前单元格。
     */
    private List<String> cellList;

    /**
     * T的类别。用于获取其实例。
     */
    private Class<T> claz;

    /**
     * 辅助类。用于从excel单元格中取值，并为T的实例设置。
     */
    private ExcelImportHelper helper = new ExcelImportHelper();

    /**
     * 将导入文件转换为T的数据列表。
     * <p>
     * 如果T是
     * {@link cn.youcredit.thread.common.service.system.FileImportService.ImportFromFile}
     * 的子类，则会写入user数据
     *
     * @return 保证返回的列表非null；保证其中的元素非null；但不保证其中元素的属性（字段）非null。<br>
     *         默认返回的是一个LinkedList。请尽量避免对该列表的随机存取。
     * @throws RuntimeException
     *         非excel指定格式（.xls或.xlsx）时，抛出此异常。
     * @see cn.youcredit.thread.common.service.system.FileImportService#importFile(byte[],
     *      cn.youcredit.thread.common.model.auth.UserInfo)
     */
    @Override
    public List<T> importFile(byte[] file, Object... params) {
        FileImportServiceAsExcel.LOGGER.info(
            LF.cn("params", "headerList", "cellList", "claz"),
            LF.cv(Arrays.toString(params)), this.headerList, this.cellList,
            this.claz);

        Workbook workbook = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(file)) {
            workbook = WorkbookFactory.create(bais);
        } catch (IOException | POIXMLException | InvalidFormatException
                | IllegalArgumentException e) {
            FileImportServiceAsExcel.LOGGER.error(LF.ns("exceptionMessage"),
                e.getMessage(), e);
            throw new RuntimeException("请选择指定格式（后缀名为.xls或.xlsx）的Excel文件！", e);
        }

        // 遍历excel文件列表，并处理数据。
        List<T> list = this.loopSheets(workbook, params);
        FileImportServiceAsExcel.LOGGER.info(LF.ns("list.size"), list.size());

        return list;
    }

    /**
     * 遍历workbook中的sheet
     *
     * @param workbook
     * @return
     * @throws RuntimeException
     */
    protected List<T> loopSheets(Workbook workbook, Object... user)
            throws RuntimeException {
        int sheetNumber = workbook.getNumberOfSheets();
        FileImportServiceAsExcel.LOGGER
            .debug(LF.ns("sheetNumber"), sheetNumber);

        // 这里无法推算实际的数量了。
        List<T> list = new LinkedList<>();
        for (int sheetIndex = 0; sheetIndex < sheetNumber; sheetIndex++) {
            FileImportServiceAsExcel.LOGGER.debug(
                LF.ns("sheetNumber", "sheetIndex"), sheetIndex, sheetNumber);
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            // 遍历sheet中的rows
            List<T> rows = this.loopRows(sheet, user);
            list.addAll(rows);
        }

        FileImportServiceAsExcel.LOGGER.debug(LF.ns("sheetNumber", "msg"),
            sheetNumber, "end");
        return list;
    }

    /**
     * 遍历sheet中的row
     *
     * @param sheet
     * @return
     * @throws RuntimeException
     */
    protected List<T> loopRows(Sheet sheet, Object... user)
            throws RuntimeException {
        // getPhysicalNumberOfRows()获得的实际行数，但里面不一定有数据。
        int rowNumber = sheet.getPhysicalNumberOfRows();

        List<T> list = new ArrayList<>(rowNumber);
        // 注意遍历条件：如果当前row为null，则跳过本次循环（continue）
        int rowIndex = 0;
        Row row = sheet.getRow(rowIndex);

        // 会导致中间有任何一行数据为null就跳出整个循环
        while (rowIndex < rowNumber && row != null) {

            if (rowIndex < this.headerList.size()) {
                // 在表头的范围内，则校验表头列是否符合条件
                // 但是不把表头封装到数据里
                FileImportServiceAsExcel.LOGGER.debug(
                    LF.ns("rowNumber", "rowIndex"), rowNumber, rowIndex);
                this.validHeader(row);
            } else {
                // 非表头的范围，则开始解析数据
                T cell = this.loopCell(row);
                // 专门处理一下用户。
                if (cell instanceof ImportFromFile) {
                    FileImportServiceAsExcel.LOGGER.debug(
                        LF.ns("instance.class"), cell.getClass());
                    ImportFromFile importFromFileInstance = (ImportFromFile) cell;
                    importFromFileInstance.setInfo(sheet.getSheetName(),
                        row.getRowNum(), user);
                }

                list.add(cell);
                FileImportServiceAsExcel.LOGGER.debug(
                    LF.ns("sheetName", "rowNumber", "rowIndex", "cell"),
                    sheet.getSheetName(), rowNumber, rowIndex, cell);
            }
            // 步进
            row = sheet.getRow(++rowIndex);
        }

        return list;
    }

    /**
     * 将表头行中的各单元格名称，与{@link #headerList}中名称对比。
     * <p>
     * 如果不一致，将抛出异常。
     *
     * @param row
     * @throws InvalidDataException
     */
    protected void validHeader(Row row) {
        // 总共的单元格数量
        int cellNumber = row.getPhysicalNumberOfCells();
        // 单元格下标
        int cellIndex = 0;
        // 解析到的单元格
        Cell cell = row.getCell(cellIndex);

        while (cellIndex < cellNumber) {
            // 某一个单元格为null时，不中断循环，继续步进。
            if (cell != null) {

                String name = cell.getStringCellValue();

                FileImportServiceAsExcel.LOGGER.debug(LF.ns("row.getRowNum()"),
                    row.getRowNum());

                // 取第RowNum行的第cellIndex列
                String validName = this.headerList.get(row.getRowNum()).get(
                    cellIndex);

                FileImportServiceAsExcel.LOGGER.debug(
                    LF.ns("cellIndex", "validName", "name"), cellIndex,
                    validName, name);

                // 如果二者不相等，则抛出异常
                if (!StringUtils.equals(validName, name)) {
                    FileImportServiceAsExcel.LOGGER.error(
                        LF.ns("msg", "cellIndex", "validName", "name"),
                        "表头取值错误！", cellIndex, validName, name);
                    throw new RuntimeException("表头取值错误！应为[" + validName
                        + "]，实为[" + name + "]");
                }
            }
            // 步进
            cell = row.getCell(++cellIndex);
        }
    }

    /**
     * 遍历一个cell
     *
     * @param row
     * @param user
     * @return 确保非null
     * @throws RuntimeException
     */
    protected T loopCell(Row row) throws RuntimeException {

        T instance;
        try {
            instance = this.claz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            FileImportServiceAsExcel.LOGGER.error(LF.ns("claz"), this.claz, e);
            throw new RuntimeException("无法获取" + this.claz + "的实例！", e);
        }
        int cellNumber = row.getPhysicalNumberOfCells();
        FileImportServiceAsExcel.LOGGER.debug(LF.ns("cellNumber"), cellNumber);

        int cellIndex = 0;
        Cell cell = row.getCell(cellIndex);

        while (cellIndex < cellNumber) {
            // cell为空，或者配置的字段名为空时，不中断循环，而是继续步进
            String fieldName = this.cellList.get(cellIndex);
            FileImportServiceAsExcel.LOGGER
                .debug(LF.ns("filedName"), fieldName);

            if (StringUtils.isNotBlank(fieldName)) {
                // 取出当前行、当前列的数据，所映射到的字段名
                // 根据字段名，填充实例。
                this.fillField(instance, fieldName, cell);
            }
            cell = row.getCell(++cellIndex);
        }
        // 能正常返回则instance非null
        assert instance.getClass() != null;
        return instance;
    }

    /**
     * 填充数据
     * <p>
     * 目前暂时只填充了Integer/Date的数据；其它数据全部按String处理。 <br>
     *
     * @param instance
     * @param fieldName
     * @param cell
     * @throws RuntimeException
     */
    protected void fillField(T instance, String fieldName, Cell cell)
            throws RuntimeException {
        Object value = this.helper.fillField(instance, fieldName, cell);
        FileImportServiceAsExcel.LOGGER.debug(LF.ns("fieldName", "value"),
            fieldName, value);

    }

    /**
     * @param headerList
     *        the {@link #headerList} to set
     */
    public void setHeaderList(List<List<String>> headerList) {
        this.headerList = headerList;
    }

    /**
     * @param cellList
     *        the {@link #cellList} to set
     */
    public void setCellList(List<String> cellList) {
        this.cellList = cellList;
    }

    /**
     * @param claz
     *        the {@link #claz} to set
     * @throws ClassNotFoundException
     */
    public void setClaz(String claz) throws ClassNotFoundException {
        this.claz = (Class<T>) Class.forName(claz);
    }

    /**
     * @param helper
     *        the {@link #helper} to set
     */
    public void setHelper(ExcelImportHelper helper) {
        this.helper = helper;
    }

    /**
     * @param claz
     *        the {@link #claz} to set
     */
    public void setClaz(Class<T> claz) {
        this.claz = claz;
    }

}
