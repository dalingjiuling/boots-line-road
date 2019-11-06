package com.line.road.framework.persistence.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
@Documented
@Component
public @interface MyBatisDao {

	public abstract String value() default "";

	public abstract Class<?> entity() default Class.class;
}