package com.ylwx.mvp.listener;

/**
 * 登出事件
 * @author wtt-ylwx
 *
 */
public interface OnLogoutListener {
	/**
	 * 登出成功
	 */
	public void logoutSuccess();
	
	/**
	 * 登出失败
	 */
	public void logoutFailed();
}
