/**
 * All Rights Reserved
 */
package net.loyintean.blog.fileimport;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * @author winters1224@163.com
 */
public abstract class CellValueTransfer4Num implements CellValueTransfer {

    /**
     */
    @Override
    public Object getValue(Cell cell) {
        assert cell.getCellType() == Cell.CELL_TYPE_NUMERIC;

        Double value = cell.getNumericCellValue();

        return value == null ? null : this.parseValue(value);
    }

    protected abstract Object parseValue(Double value);

}

class CellValueTransfer4Num2Int extends CellValueTransfer4Num {

    /**
     */
    @Override
    protected Object parseValue(Double value) {

        return value.intValue();
    }

}

class CellValueTransfer4Num2Str extends CellValueTransfer4Num {

    /**
     */
    @Override
    protected Object parseValue(Double value) {
        return value.toString();
    }

}

class CellValueTransfer4Num2Date implements CellValueTransfer {

    @Override
    public Object getValue(Cell cell) {
        assert cell.getCellType() == Cell.CELL_TYPE_NUMERIC;

        Object value = null;
        if (DateUtil.isCellDateFormatted(cell)) {
            value = cell.getDateCellValue();
        } else {
            value = DateUtil.getJavaDate(cell.getNumericCellValue());
        }

        return value;
    }

}

class CellValueTransfer4Num2BigDecimal extends CellValueTransfer4Num {

    /**
     */
    @Override
    protected Object parseValue(Double value) {

        return new BigDecimal(value.toString());
    }

}