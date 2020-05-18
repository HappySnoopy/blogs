package net.loyintean.springmvcbase.common.bean;

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
@Data
public abstract class BaseResult<T> implements Serializable {

    protected static final String CODE_SUCCESS = "0000";

    protected static final String MSG_SUCCESS = "调用成功";

    /** The Data. */
    protected T data;
    /** The Code. */
    private String code;
    /** The Message. */
    private String message;

    protected BaseResult(T data) {
        super();
        this.code = CODE_SUCCESS;
        this.message = MSG_SUCCESS;
        this.data = data;
    }

    public boolean isSuccess() {
        return CODE_SUCCESS.equals(code);
    }

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
