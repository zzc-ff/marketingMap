package com.example.stringbootbo.common;

import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
public @interface IsItNecessary {
    boolean key() default false;
}
