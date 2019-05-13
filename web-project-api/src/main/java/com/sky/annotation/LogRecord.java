package com.sky.annotation;

import java.lang.annotation.*;

/**
 * Created by ThinkPad on 2019/5/13.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {
    String name() default "";
    String description() default "";
    boolean printReturn() default true;
}
