/**
 * 
 * All Rights Reserved
 */
package net.loyintean.fileimportor.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Snoopy
 */
class ExcelImportHelper {
    private static final Logger LOGGER = LoggerFactory
        .getLogger(ExcelImportHelper.class);

    /**
     * 数组长度为6，处理各个cellType
     */
    private final Map<String, FileImportServiceAsExcel.CellValueTransfer>[] transers;

    /**
     * 初始化 {@link #transers}。
     * <p>
     * 确保transers中六个下标上都有数据。
     */
    public ExcelImportHelper() {
        this.transers = new HashMap[6];

        // CELL_TYPE_NUMERIC
        Map<String, FileImportServiceAsExcel.CellValueTransfer> transferMap4Num = new TransferMap(11);
        // -> Integer
        transferMap4Num.put(Integer.class.getName(),
            new CellValueTransfer4Num2Int());
        // -> String
        transferMap4Num.put(String.class.getName(),
            new CellValueTransfer4Num2Str());
        // -> Date
        transferMap4Num.put(Date.class.getName(),
            new CellValueTransfer4Num2Date());
        // -> BigDecimal
        transferMap4Num.put(BigDecimal.class.getName(),
            new CellValueTransfer4Num2BigDecimal());
        this.transers[Cell.CELL_TYPE_NUMERIC] = transferMap4Num;

        // CELL_TYPE_STRING
        Map<String, FileImportServiceAsExcel.CellValueTransfer> transferMap4Str = new TransferMap(11);
        // -> Integer
        transferMap4Str.put(Integer.class.getName(),
            new CellValueTransfer4Str2Int());
        // -> String
        transferMap4Str.put(String.class.getName(),
            new CellValueTransfer4Str2Str());
        // -> Date
        transferMap4Str.put(Date.class.getName(),
            new CellValueTransfer4Str2Date());
        // -> BigDecimal
        transferMap4Str.put(BigDecimal.class.getName(),
            new CellValueTransfer4Str2BigDecimal());
        this.transers[Cell.CELL_TYPE_STRING] = transferMap4Str;

        // CELL_TYPE_FORMULA
        // 默认没有处理
        this.transers[Cell.CELL_TYPE_FORMULA] = new TransferMap(0);

        // CELL_TYPE_BLANK
        // 默认没有处理
        this.transers[Cell.CELL_TYPE_BLANK] = new TransferMap(0);

        // CELL_TYPE_BOOLEAN
        // 默认没有处理
        this.transers[Cell.CELL_TYPE_BOOLEAN] = new TransferMap(0);

        // CELL_TYPE_ERROR
        // 默认没有处理
        this.transers[Cell.CELL_TYPE_ERROR] = new TransferMap(0);

    }

    /**
     * 将cell中的数据填充到instance的fieldName字段中。
     * <p>
     * 并返回实际的数据
     *
     * @param instance
     *        待设值的实例
     * @param fieldName
     *        待设值的字段名
     * @param cell
     *        取值的单元格
     * @return 返回cell中的数据。其类型视instance.fieldName的类型和cell的cellType而定。
     *         <p>
     *         不保证非null
     */
    protected Object fillField(Object instance, String fieldName, Cell cell) {

        Class<?> claz;
        try {
            claz = PropertyUtils.getPropertyType(instance, fieldName);
        } catch (IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            ExcelImportHelper.LOGGER.error(
                    "instance.class:{}, exceptionMessage:{}",
                    instance.getClass(), e.getMessage(), e);
            throw new RuntimeException("无法获取字段" + fieldName + "的正确类型！", e);
        }

        ExcelImportHelper.LOGGER.debug("cellType:{}, fieldName:{}, claz:{}",
                cell.getCellType(), fieldName, claz);
        FileImportServiceAsExcel.CellValueTransfer transfer = this.transers[cell.getCellType()].get(claz
                .getName());

        Object value = transfer.getValue(cell);
        ExcelImportHelper.LOGGER.debug("cellType:{}, fieldName:{}, value:{}",
                claz, transfer, value);

        try {
            PropertyUtils.setProperty(instance, fieldName, value);
        } catch (IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            ExcelImportHelper.LOGGER.error(
                    "cellType:{}, fieldName:{}, value:{}, ex.message:{}",
                    instance, fieldName, value, e.getMessage(), e);
            throw new RuntimeException("无法为字段" + fieldName + "设值：" + value
                + "！", e);
        }

        ExcelImportHelper.LOGGER.debug("fieldName:{},value:{}", fieldName,
                value);
        return value;
    }

    /**
     * 增加CELL_TYPE_NUMERIC的转换器映射
     *
     * @param transersMap
     */
    public void setTransers4Numeric(Map<String, FileImportServiceAsExcel.CellValueTransfer> transersMap) {
        this.transers[Cell.CELL_TYPE_NUMERIC].putAll(transersMap);
    }

    /**
     * 增加CELL_TYPE_STRING的转换器映射
     *
     * @param transersMap
     */
    public void setTransers4String(Map<String, FileImportServiceAsExcel.CellValueTransfer> transersMap) {
        this.transers[Cell.CELL_TYPE_STRING].putAll(transersMap);
    }

    /**
     * 增加CELL_TYPE_FORMULA的转换器映射
     *
     * @param transersMap
     */
    public void setTransers4Formula(Map<String, FileImportServiceAsExcel.CellValueTransfer> transersMap) {
        this.transers[Cell.CELL_TYPE_FORMULA].putAll(transersMap);
    }

    /**
     * 增加CELL_TYPE_BLANK的转换器映射
     *
     * @param transersMap
     */
    public void setTransers4Blank(Map<String, FileImportServiceAsExcel.CellValueTransfer> transersMap) {
        this.transers[Cell.CELL_TYPE_BLANK].putAll(transersMap);
    }

    /**
     * 增加CELL_TYPE_BOOLEAN的转换器映射
     *
     * @param transersMap
     */
    public void setTransers4Boolean(Map<String, FileImportServiceAsExcel.CellValueTransfer> transersMap) {
        this.transers[Cell.CELL_TYPE_BOOLEAN].putAll(transersMap);
    }

    /**
     * 增加CELL_TYPE_ERROR的转换器映射
     *
     * @param transersMap
     */
    public void setTransers4Error(Map<String, FileImportServiceAsExcel.CellValueTransfer> transersMap) {
        this.transers[Cell.CELL_TYPE_ERROR].putAll(transersMap);
    }

    private class TransferMap extends HashMap<String, FileImportServiceAsExcel.CellValueTransfer> {
        private static final long serialVersionUID = 5355242374583108179L;

        public TransferMap(int capability) {
            super(capability);
        }

        /**
         * 返回null的转换器。
         * <p>
         * 当transers中没有获取到正常的转换器时，将使用它来直接返回null
         */
        private FileImportServiceAsExcel.CellValueTransfer initTransfer = cell -> null;

        @Override
        public FileImportServiceAsExcel.CellValueTransfer get(Object key) {
            if (this.containsKey(key)) {
                return super.get(key);
            } else {
                return this.initTransfer;
            }
        }
    }

}
