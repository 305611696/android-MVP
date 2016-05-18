package com.ylwx.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.ylwx.mvp.annotation.ViewUtils;
import com.ylwx.mvp.ui.view.CustomToast;

/**
 * 基础类
 * @author wtt-ylwx
 * 2016年5月5日8:30:56
 */
public class BaseActivity extends Activity {

	public boolean toTarget = false;
	
	/**
	 * 复写父类方法
	 */
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
		//添加注解
		ViewUtils.inject(this);
	}
	
	/**
	 * 复写父类方法
	 */
	@Override
	public void setContentView(View view) {
		// TODO Auto-generated method stub
		super.setContentView(view);
		//添加注解
		ViewUtils.inject(this);
	}

	/**
	 * 复写父类方法
	 */
	@Override
	public void setContentView(View view, LayoutParams params) {
		// TODO Auto-generated method stub
		super.setContentView(view, params);
		//添加注解
		ViewUtils.inject(this);
	}

	/**
	 * 显示简单的Toast，显示时间为1s
	 * @param text 显示内容
	 */
	public void showSimpleToast(String text){
		showToast(text, 1000);
	}
	
	/**
	 * 显示Toast，指定显示时长
	 * @param text 显示内容
	 * @param duration 显示时间
	 */
	public void showToast(String text, int duration){
		CustomToast.showToast(this, text, duration);
	}
	
	/**
	 * 跳转到指定activity
	 * @param cls
	 */
	public void toTarget(Class<?> cls){
		Intent intent = new Intent();
		toTarget(cls, intent);
	}
	
	/**
	 * 跳转到指定activity
	 * @param cls
	 * @param intent
	 */
	public void toTarget(Class<?> cls, Intent intent){
		toTarget = true;
		intent.setClass(this, cls);
		startActivity(intent);
		finish();
	}
	
}
