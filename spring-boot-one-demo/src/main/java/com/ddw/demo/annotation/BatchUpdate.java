package com.ddw.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface BatchUpdate {

	Class<?>[] groups() default { };

	/**
     * @return value the element can be modified
	 */
	boolean value() default true;
}