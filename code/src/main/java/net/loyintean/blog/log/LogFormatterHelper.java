package net.loyintean.blog.log;

/**
 * 辅助类
 * <p>
 * 通过不同的子类实现，替换不同的格式。
 *
 * @author linjun
 */
interface LogFormatterHelper {
    /**
     * @param names
     * @return
     */
    String names(String... names);

    /**
     * 将指定的name-value对，按照自己的格式进行格式化。
     *
     * @param name
     * @param value
     * @return
     */
    String format(String name, String value);
}

/**
 * name=value的格式化字符串。
 *
 * @author linjun
 */
class LogFormatHelperAsKeyValue implements LogFormatterHelper {

    /**
     * 如果names = [name1,name2,name3,{},name4]，则：
     * 返回结果为：name1={},name2={},name3={},{},name4={},<br>
     * 末尾会有逗号。不做处理。
     * <p>
     *
     * @see cn.youcredit.thread.common.log.LF#ns(java.lang.String[])
     */
    @Override
    public String names(String... names) {
        StringBuilder builder;

        if (names != null && names.length > 0) {
            // 假定长度
            builder = new StringBuilder(names.length * 2);

            for (String name : names) {
                builder.append(name);
                builder.append("={},");
            }

        } else {
            builder = new StringBuilder();
        }

        return builder.toString();
    }

    /**
     * @see cn.youcredit.thread.common.log.LogFormatterHelper#format(java.lang.String,
     *      java.lang.String)
     * @return "name=value,"
     */
    @Override
    public String format(String name, String value) {

        StringBuilder buider = new StringBuilder(name.length() + value.length()
            + 2);
        buider.append(name);
        buider.append('=');
        buider.append(value);
        buider.append(',');

        return buider.toString();
    }
}