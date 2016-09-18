/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.fileimport;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author linjun
 */
public abstract class CellValueTransfer4Str implements CellValueTransfer {
    /**
     * @see cn.youcredit.thread.common.service.system.CellValueTransfer
     *      #getValue(org.apache.poi.ss.usermodel.Cell)
     */
    @Override
    public Object getValue(Cell cell) {
        assert cell.getCellType() == Cell.CELL_TYPE_STRING;

        String value = cell.getStringCellValue();

        return value == null ? null : this.parseValue(value);
    }

    protected abstract Object parseValue(String value);
}

class CellValueTransfer4Str2Int extends CellValueTransfer4Str {

    /**
     * @see cn.youcredit.thread.common.service.system.CellValueTransfer4Str
     *      #parseValue(java.lang.Double)
     */
    @Override
    protected Object parseValue(String value) {

        return Integer.parseInt(value);
    }

}

class CellValueTransfer4Str2Str extends CellValueTransfer4Str {

    /**
     * @see cn.youcredit.thread.common.service.system.CellValueTransfer4Str
     *      #parseValue(java.lang.Double)
     */
    @Override
    protected Object parseValue(String value) {
        // linjun 2016-01-19 THREAD-9904 排除空格的干扰。
        return StringUtils.trim(value);
    }

}

class CellValueTransfer4Str2Date extends CellValueTransfer4Str {

    /**
     * 按最长匹配开始尝试。<br>
     * linjun 2016-01-19 THREAD-9904 增加一种格式
     */
    private static final String[] FORMAT = new String[] {
        "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
        "yyyy/MM/dd" };

    /**
     * @see cn.youcredit.thread.common.service.system.CellValueTransfer4Str
     *      #parseValue(java.lang.Double)
     */
    @Override
    protected Object parseValue(String value) {

        Date result = null;
        // 必须从最长匹配开始尝试，否则可能用短时间格式匹配上了长时间格式
        for (int i = 0; i < CellValueTransfer4Str2Date.FORMAT.length
            && result == null; i++) {
            try {
                result = new SimpleDateFormat(
                    CellValueTransfer4Str2Date.FORMAT[i]).parse(value);
            } catch (ParseException e) {
                result = null;
            }
        }

        return result;
    }

}

class CellValueTransfer4Str2BigDecimal extends CellValueTransfer4Str {

    /**
     * @see cn.youcredit.thread.common.service.system.CellValueTransfer4Str
     *      #parseValue(java.lang.Double)
     */
    @Override
    protected Object parseValue(String value) {
        return new BigDecimal(value);
    }

}