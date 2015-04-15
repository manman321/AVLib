package com.snicesoft.avlib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午10:17:13
 * @version V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
	public int value() default 0;

	public int background() default 0;

	public int backgroundColor() default 0;

	public int src() default 0;
}
