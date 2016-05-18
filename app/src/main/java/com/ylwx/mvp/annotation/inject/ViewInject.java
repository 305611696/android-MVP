package com.ylwx.mvp.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 带点击事件注解
 * @author wtt-ylwx
 * @date 创建时间：2016-5-5 下午3:08:54
 */
@Target(ElementType.FIELD) //作用于成员变量
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface ViewInject {

	/**
	 * 控件ID
	 * @return id
	 * @date 创建时间：2016-5-5 下午3:10:11
	 */
	int id();
	
	/**
	 * 点击事件
	 * @see xxx(View v)
	 * @return void
	 * @date 创建时间：2016-5-5 下午3:09:53
	 */
	String click() default "";
	
	/**
	 * 长按事件
	 * @see xxx(View v)
	 * @return void
	 * @date 创建时间：2016-5-5 下午3:10:47
	 */
	String longClick() default "";
	
	/**
	 * 触摸事件
	 * @see xxx(View v, MotionEvent event)
	 * @return void
	 * @date 创建时间：2016-5-5 下午3:11:52
	 */
	String touch() default "";
}
