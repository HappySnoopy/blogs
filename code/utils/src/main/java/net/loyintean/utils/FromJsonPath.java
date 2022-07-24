package net.loyintean.utils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JsonDeserialize(using = JsonPathDeserializer.class)
public @interface FromJsonPath {
    String fromPath() default "";
}
