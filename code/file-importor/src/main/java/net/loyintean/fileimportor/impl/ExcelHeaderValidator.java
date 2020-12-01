package net.loyintean.fileimportor.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * validate the headers while importing excel file
 *
 * @author Snoopy
 */
interface ExcelHeaderValidator {

    /**
     * validate the row is an legal header.
     *
     * @param row
     */
    void validate(Row row);

    /**
     * get the size of header.
     * <p>
     * max row number of header.
     *
     * @return
     */
    int getHeaderSize();

    /**
     * put the headerList
     *
     * @param headerList
     */
    void setHeaderList(List<List<String>> headerList);
}

class ExcelHeaderValidatorByName implements ExcelHeaderValidator {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ExcelHeaderValidatorByName.class);

    /**
     * 表头的映射列表。要求excel中表头全部是文本格式。
     * <p>
     * 本质上是一个二维数据。<br>
     * 第一维下标对应行号，第二位下标对应列号。<br>
     */
    private List<List<String>> headerList;

    /*
     * (non-Javadoc)
     * @see
     * net.loyintean.blog.fileimport.ExcelHeaderValidator#validate(org.apache
     * .poi.ss.usermodel.Row)
     */
    @Override
    public void validate(Row row) {
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

                ExcelHeaderValidatorByName.LOGGER.debug(
                        "row.getRowNum():{}", row.getRowNum());

                // 取第RowNum行的第cellIndex列
                String validName = this.headerList.get(row.getRowNum()).get(
                        cellIndex);

                ExcelHeaderValidatorByName.LOGGER.debug(
                        "cellIndex:{}, validName:{}, name:{}", cellIndex,
                        validName, name);

                // 如果二者不相等，则抛出异常
                if (!StringUtils.equals(validName, name)) {
                    ExcelHeaderValidatorByName.LOGGER.error(
                            "msg:{} cellIndex:{}, validName:{}, name:{}",
                            "表头取值错误！", cellIndex, validName, name);
                    throw new RuntimeException("表头取值错误！应为[" + validName
                            + "]，实为[" + name + "]");
                }
            }
            // 步进
            cell = row.getCell(++cellIndex);
        }
    }

    @Override
    public void setHeaderList(List<List<String>> headerList) {
        this.headerList = headerList;
    }

    /**
     * (non-Javadoc)
     *
     * @see net.loyintean.blog.fileimport.ExcelHeaderValidator#getHeaderRownum()
     */
    @Override
    public int getHeaderSize() {
        return this.headerList == null ? 0 : this.headerList.size();
    }
}
