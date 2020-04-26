/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.fileimport;

import org.apache.poi.ss.usermodel.Cell;

/**
 * excel单元格数据转换器
 *
 * @author winters1224@163.com
 */
public interface CellValueTransfer {

    /**
     * 将cell中的数据取出来，并转换为特定类型、特定格式。
     *
     * @param cell
     * @return 可能为null
     */
    Object getValue(Cell cell);
}
