package com.ddw.demo.annotation;

import org.springframework.data.annotation.QueryAnnotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
//@QueryAnnotation
@Documented

public @interface NativeSql {
    /**
     * Defines the JPA query to be executed when the annotated method is called.
     */
    String value() default "";
}
