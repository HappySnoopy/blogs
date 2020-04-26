/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.fileimport;

import net.loyintean.blog.fileimport.FileImportService.ImportFromFile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelModel implements ImportFromFile {

    private String name;

    private Integer age;

    private Object someObject;

    public Object getSomeObject() {
        return this.someObject;
    }

    public void setSomeObject(Object someObject) {
        this.someObject = someObject;
    }

    private Date birthday;

    private Integer rowNum;

    private String sheetName;

    /**
     * @return the {@link #name}
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *        the {@link #name} to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the {@link #age}
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * @param age
     *        the {@link #age} to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the {@link #birthday}
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * @param birthday
     *        the {@link #birthday} to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ExcelModel [");
        if (this.name != null) {
            builder.append("name=");
            builder.append(this.name);
            builder.append(", ");
        }
        if (this.age != null) {
            builder.append("age=");
            builder.append(this.age);
            builder.append(", ");
        }
        if (this.someObject != null) {
            builder.append("someObject=");
            builder.append(this.someObject);
            builder.append(", ");
        }
        if (this.birthday != null) {
            builder.append("birthday=");
            builder.append(new SimpleDateFormat("yyyy-MM-dd")
                .format(this.birthday));
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * @return the {@link #rowNum}
     */
    public Integer getRowNum() {
        return this.rowNum;
    }

    /**
     * @return the {@link #sheetName}
     */
    public String getSheetName() {
        return this.sheetName;
    }

    @Override
    public void setInfo(String sheetName, int rowNum, Object... params) {
        this.sheetName = sheetName;
        this.rowNum = rowNum;
        this.someObject = params[0];

    }
}
