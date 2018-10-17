package com.jqmkj.WaterResponseSystem.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormSearchCondition {

    public String beginName() default "";

    public String endName() default "";

    public String name() default "";

    public String like() default "";

}
