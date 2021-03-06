/**
 * 
 * All Rights Reserved
 */
package net.loyintean.fileimportor;

import java.util.List;

/**
 * @param <T> 从导入文件中解析出来的数据类
 * @author Snoopy
 */
public interface FileImportService<T> {

    /**
     * 由指定用户user导入文件file
     *
     * TODO linjun 2020-12-01 考虑使用InputStreamn。更节约内存。
     *
     * @param file
     *        导入文件。为了兼容各种文件类型（File，MultipartFile，InputStream等，使用更底层的字节数组。
     * @param params
     *        额外数据
     * @return 导入文件的到的数据。
     */
    List<T> importFile(byte[] file, Object... params);

    /**
     * 导入并解析文件时才需要使用的标记类接口。
     *
     * @author Snoopy
     */
    interface ImportFromFile {

        /**
         * 将 {@link FileImportService#importFile(byte[], Object[])}
         * 中的UserInfo注入相关实例中。
         *
         * @param sheetName
         * @param rowNum
         * @param params
         * @param user
         */
        void setInfo(String sheetName, int rowNum, Object... params);
    }
}
