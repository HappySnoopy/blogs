/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.fileimport;

import java.util.List;

import net.loyintean.blog.fileimport.FileImportService.ImportFromFile;
import net.loyintean.blog.log.LF;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * parse a row into java Pojo.
 *
 * @author linjun
 */
interface ExcelDataParser {

    /**
     * may return null
     *
     * @param row
     * @param params
     * @return
     */
    Object parseRow(Row row, Object... params);

    void setFieldNames(List<String> fieldNames);

    void setClaz(Class<?> claz);
}

class ExcelDataParserByFieldName implements ExcelDataParser {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(ExcelHeaderValidatorByName.class);

    /**
     * 表内数据的映射列表
     * <p>
     * 列表下标对应每一行中单元格的下标。<br>
     * 如果列表数据为null或空字符串，则跳过当前单元格。
     */
    private List<String> fieldNames;

    /**
     * T的类别。用于获取其实例。
     */
    private Class<?> claz;

    /**
     * 辅助类。用于从excel单元格中取值，并为T的实例设置。
     */
    private ExcelImportHelper helper = new ExcelImportHelper();

    /*
     * (non-Javadoc)
     * @see
     * net.loyintean.blog.fileimport.ExcelDataParser#parseRow(org.apache.poi
     * .ss.usermodel.Row, java.lang.Object[])
     */
    @Override
    public Object parseRow(Row row, Object... params) {

        Object instance;
        try {
            instance = this.claz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            ExcelDataParserByFieldName.LOGGER
                .error(LF.ns("claz"), this.claz, e);
            throw new RuntimeException("无法获取" + this.claz + "的实例！", e);
        }
        int cellNumber = row.getPhysicalNumberOfCells();
        ExcelDataParserByFieldName.LOGGER
            .debug(LF.ns("cellNumber"), cellNumber);

        int cellIndex = 0;
        Cell cell = row.getCell(cellIndex);

        boolean allCellBlank = true;
        while (cellIndex < cellNumber) {
            // cell为空，或者配置的字段名为空时，不中断循环，而是继续步进
            String fieldName = this.fieldNames.get(cellIndex);
            ExcelDataParserByFieldName.LOGGER.debug(LF.ns("filedName"),
                fieldName);

            if (StringUtils.isNotBlank(fieldName) && cell != null) {
                // 取出当前行、当前列的数据，所映射到的字段名
                // 根据字段名，填充实例。
                Object value = this.helper.fillField(instance, fieldName, cell);
                if (value != null) {
                    allCellBlank = false;
                }
            }
            cell = row.getCell(++cellIndex);
        }
        if (allCellBlank) {
            instance = null;
        } else if (instance instanceof ImportFromFile) {
            ExcelDataParserByFieldName.LOGGER.debug(LF.ns("instance.class"),
                instance.getClass());
            ImportFromFile importFromFileInstance = (ImportFromFile) instance;
            importFromFileInstance.setInfo(row.getSheet().getSheetName(),
                row.getRowNum(), params);

            // 能正常返回则instance非null
            assert instance.getClass() != null;
        }
        return instance;
    }

    /*
     * (non-Javadoc)
     * @see
     * net.loyintean.blog.fileimport.ExcelDataParser#setCellList(java.util.List)
     */
    @Override
    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    /**
     * @param claz
     *        the {@link #claz} to set
     */
    @Override
    public void setClaz(Class<?> claz) {
        this.claz = claz;
    }

    /**
     * @param helper
     *        the {@link #helper} to set
     */
    public void setHelper(ExcelImportHelper helper) {
        this.helper = helper;
    }
}