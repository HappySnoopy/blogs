package net.loyintean.springmvcbase.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * The type Result.
 *
 * @param <T> the type parameter
 * @author 林俊 <junlin8@creditease.cn>
 * @date 2020 -05-18
 */
@AllArgsConstructor
@Data
public abstract class BaseResult<T> implements Serializable {

    public static final String SUCCESS = "0000";

    /** The Code. */
    private String code;
    /** The Message. */
    private String message;
    /** The Data. */
    private T data;

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
