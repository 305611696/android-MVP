package com.ylwx.mvp.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

import com.ylwx.mvp.annotation.inject.OnClick;
import com.ylwx.mvp.annotation.inject.ViewInject;

/**
 * 注解工具类
 * @author wtt-ylwx
 * @date 创建时间：2016-5-5 上午8:40:37
 */
public class ViewUtils {
	
	private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";  
	
	/**
	 * 初始化注解
	 * @param context
	 * @date 创建时间：2016-5-5 上午8:40:08
	 */
	public static void inject(Context context){
		injectViews(context);
		injectClick(context);
	}
	
	/**
	 * 作用于方法的点击事件
	 * @param context
	 * @date 创建时间：2016-5-5 下午3:12:17
	 */
	private static void injectClick(final Context context){
		/**
		 * 反射获取类
		 */
		Class<? extends Context> clazz = context.getClass();
		/**
		 * 获取所有方法
		 */
		Method[] methods = clazz.getDeclaredMethods();
		for (final Method method : methods) {
			//获取有注解了@OnClick方法
			OnClick onClick = method.getAnnotation(OnClick.class);
			if(onClick!=null){
				/**
				 * 获取注解id
				 */
				int[] viewIds = onClick.id(); 
				try {
					/**
					 * 遍历
					 */
					for(final int viewId : viewIds){
						//找到指定对应参数方法
						Method findView = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
						//取消访问权限检查 提高访问速度20倍
						findView.setAccessible(true);
						//获取View
						View view = (View)findView.invoke(context, viewId);
						//设置点击事件
						view.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								try {
									//调用方法
									method.invoke(context, v);
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/** 
     * 注入所有的控件 
     *  
     * @param activity 
     */  
    private static void injectViews(final Context context)  
    {  
    	//获取类型类
    	Class<? extends Context> clazz = context.getClass();
    	//获取所有成员
        Field[] fields = clazz.getDeclaredFields();  
        // 遍历所有成员变量  
        for (Field field : fields)  
        {  
        	//获取所有注解@ViewInject的成员
            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);  
            if (viewInjectAnnotation != null)  
            {  
            	//如果注解了@ViewInject获取注解id
                int viewId = viewInjectAnnotation.id();  
                if (viewId != -1)  
                {  
                    Log.d("TAG", viewId+"");  
                    // 初始化View  
                    try  
                    {  
                    	//获取指定参数的方法
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                        //获取实例
                        View resView = (View)method.invoke(context, viewId);
                        //取消访问检查 如果不取消成员权限设置为private报异常 提高性能
                        field.setAccessible(true);  
                        //设置字段值
                        field.set(context, resView);  
                        
                        //检测是否注解click
                        String viewClick = viewInjectAnnotation.click();  
                        if(!viewClick.equals("")){
                        	//如果注解了获取有注解的方法
                        	final Method click = clazz.getMethod(viewClick, View.class);
                        	//取消访问检查 如果不取消成员权限设置为private报异常 提高性能
                        	click.setAccessible(true);
                        	//设置字段click事件
                        	resView.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									try {
										//回调click
										click.invoke(context, v);
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
                        }
                        
                        //检测是否注解longClick
                        String longClick = viewInjectAnnotation.longClick();  
                        if(!longClick.equals("")){
                        	//如果注解了获取有注解的方法
                        	final Method mLongClick = clazz.getMethod(longClick, View.class);
                        	//取消访问检查 如果不取消成员权限设置为private报异常 提高性能
                        	mLongClick.setAccessible(true);
                        	//设置字段longClick事件
                        	resView.setOnLongClickListener(new OnLongClickListener() {
								
								@Override
								public boolean onLongClick(View v) {
									// TODO Auto-generated method stub
									try {
										//回调longClick
										mLongClick.invoke(context, v);
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									return false;
								}
							});
                        }
                        
                      //检测是否注解touch
                        String touch = viewInjectAnnotation.touch();  
                        if(!touch.equals("")){
                        	//如果注解了获取有注解的方法
                        	final Method mTouch = clazz.getMethod(touch, View.class, MotionEvent.class);
                        	//取消访问检查 如果不取消成员权限设置为private报异常 提高性能
                        	mTouch.setAccessible(true);
                        	//设置字段touch事件
                        	resView.setOnTouchListener(new OnTouchListener() {

								@Override
								public boolean onTouch(View v, MotionEvent event) {
									// TODO Auto-generated method stub
									try {
										//回调touch
										mTouch.invoke(context, v, event);
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									return false;
								}
								
							});
                        }
                        
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
  
        }  
  
    }  
}
