package com.ylwx.mvp.ui.splash.interfaces;

/**
 * Splash
 * @author wtt-ylwx
 * @date 创建时间：2016-5-9 下午3:00:26
 */
public interface ISplashView {
	
	/**
	 * 延迟跳转
	 * @param int seconds
	 * @date 创建时间：2016-5-9 下午3:17:50
	 */
	public boolean delayedToTarget(int seconds);
	
	/**
	 * 设置界面
	 * @param newsPager
	 * @date 创建时间：2016-5-9 下午3:00:37
	 */
	public void settingPagerImage(String newsPager);
	
	/**
	 * 设置URL
	 * @param url
	 * @date 创建时间：2016-5-9 下午3:00:53
	 */
	public void settingUrl(String url);
	
}
