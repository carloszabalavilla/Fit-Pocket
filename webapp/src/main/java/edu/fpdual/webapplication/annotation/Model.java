package edu.fpdual.webapplication.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Model {
    String date() default "";

    String version() default "";

    String type() default "";
}
