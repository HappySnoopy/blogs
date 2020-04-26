/**
 *
 * All Rights Reserved
 */
package net.loyintean.blog.fileimport;

import net.loyintean.blog.log.LF;
import org.apache.poi.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 导入excel的服务框架
 * <p>
 * 使用标准化的org.apache.poi.ss.usermodel.*接口，兼容2003和2007格式。
 *
 * @author winters1224@163.com
 * @param <T>
 */
public class FileImportServiceAsExcel<T> implements FileImportService<T> {
    private static final Logger LOGGER = LoggerFactory
        .getLogger(FileImportServiceAsExcel.class);

    /**
     * 表头校验器
     */
    private ExcelHeaderValidator headerValidator;

    /**
     * 数据解析器
     */
    private ExcelDataParser dataParser;

    /**
     * 将导入文件转换为T的数据列表。
     * <p>
     * 如果T是 {@link FileImportService.ImportFromFile} 的子类，则会写入user数据
     *
     * @return 保证返回的列表非null；保证其中的元素非null；但不保证其中元素的属性（字段）非null。<br>
     *         默认返回的是一个LinkedList。请尽量避免对该列表的随机存取。
     * @throws RuntimeException
     *         非excel指定格式（.xls或.xlsx）时，抛出此异常。
     * @see FileImportService#importFile(byte[], Object[])
     */
    @Override
    public List<T> importFile(byte[] file, Object... params) {
        FileImportServiceAsExcel.LOGGER.info(
            LF.cn("params", "headerValidator", "dataParser"),
            LF.cv(Arrays.toString(params)), this.headerValidator,
            this.dataParser);

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
    protected List<T> loopSheets(Workbook workbook, Object... user) {
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
    protected List<T> loopRows(Sheet sheet, Object... user) {
        // getPhysicalNumberOfRows()获得的实际行数，但里面不一定有数据。
        int rowNumber = sheet.getPhysicalNumberOfRows();

        List<T> list = new ArrayList<>(rowNumber);
        // 注意遍历条件：如果当前row为null，则跳过本次循环（continue）
        int rowIndex = 0;
        Row row = sheet.getRow(rowIndex);

        // 会导致中间有任何一行数据为null就跳出整个循环
        while (rowIndex < rowNumber && row != null) {
            if (rowIndex < this.headerValidator.getHeaderSize()) {
                // 在表头的范围内，则校验表头列是否符合条件
                // 但是不把表头封装到数据里
                FileImportServiceAsExcel.LOGGER.debug(
                    LF.ns("rowNumber", "rowIndex"), rowNumber, rowIndex);
                this.headerValidator.validate(row);
            } else {
                // 非表头的范围，则开始解析数据
                T instance = (T) this.dataParser.parseRow(row, user);
                if (instance != null) {
                    list.add(instance);
                }
                FileImportServiceAsExcel.LOGGER.debug(
                    LF.ns("sheetName", "rowNumber", "rowIndex", "cell"),
                    sheet.getSheetName(), rowNumber, rowIndex, instance);
            }
            // 步进
            row = sheet.getRow(++rowIndex);
        }

        return list;
    }

    /**
     * init a {@link #headerValidator} as {@lin ExcelHeaderValidatorByName} if
     * it's null. <br>
     * then set param to it.
     *
     * @param headerList
     *        the {@link #headerValidator} to set
     */
    public void setHeaderList(List<List<String>> headerList) {
        if (this.headerValidator == null) {
            this.headerValidator = new ExcelHeaderValidatorByName();
        }
        this.headerValidator.setHeaderList(headerList);
    }

    /**
     * @param cellList
     *        the {@link #dataParser} to set
     */
    public void setCellList(List<String> cellList) {
        if (this.dataParser == null) {
            this.dataParser = new ExcelDataParserByFieldName();
        }
        this.dataParser.setFieldNames(cellList);
    }

    /**
     * @param helper
     *        the {@link #dataParser} to set
     */
    public void setHelper(ExcelImportHelper helper) {

        if (this.dataParser == null) {
            this.dataParser = new ExcelDataParserByFieldName();
        }

        if (this.dataParser instanceof ExcelDataParserByFieldName) {
            ((ExcelDataParserByFieldName) this.dataParser).setHelper(helper);
        }
    }

    /**
     * @param claz
     *        the {@link #dataParser} to set
     */
    public void setClaz(Class<T> claz) {
        if (this.dataParser == null) {
            this.dataParser = new ExcelDataParserByFieldName();
        }
        this.dataParser.setClaz(claz);
    }

    /**
     * @param headerValidator
     *        the {@link #headerValidator} to set
     */
    public void setHeaderValidator(ExcelHeaderValidator headerValidator) {
        this.headerValidator = headerValidator;
    }

    /**
     * @param dataParser
     *        the {@link #dataParser} to set
     */
    public void setDataParser(ExcelDataParser dataParser) {
        this.dataParser = dataParser;
    }

}
