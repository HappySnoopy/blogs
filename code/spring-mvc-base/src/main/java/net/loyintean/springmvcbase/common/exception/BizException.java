package net.loyintean.springmvcbase.common.exception;

import lombok.Getter;

public class BizException extends RuntimeException {
    /** The Code. */
    @Getter
    private final String code;

    /**
     * Instantiates a new Biz exception.
     *
     * @param code    the code
     * @param message the message
     */
    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }
}
