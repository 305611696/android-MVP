package com.ylwx.mvp.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wtt-ylwx
 * @date 创建时间：2016-5-5 下午2:28:17
 */
@Target(ElementType.METHOD) //作用于方法
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface OnClick {
	
	/**
	 * 参数 R.id.xxx 或 {R.id.xxx, R.id.xxx}
	 * @return
	 * @date 创建时间：2016-5-5 下午3:07:33
	 */
	int[] id();
}
