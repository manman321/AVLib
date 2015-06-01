package com.snicesoft.avlib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:52:28
 * @version V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataBind {
	public int id();

	public DataType dataType() default DataType.STRING;

	public int loadingResId() default 0;

	public int failResId() default 0;

	/**
	 * 前缀
	 * 
	 * @return
	 */
	public String prefix() default "";

	/**
	 * 后缀
	 * 
	 * @return
	 */
	public String suffix() default "";

	/**
	 * 格式化Date
	 * 
	 * @return
	 */
	public String pattern() default "";

}
