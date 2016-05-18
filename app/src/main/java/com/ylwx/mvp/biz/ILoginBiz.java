package com.ylwx.mvp.biz;

import com.ylwx.mvp.listener.OnLoginListener;
import com.ylwx.mvp.listener.OnLogoutListener;

/**
 * 
 * @author wtt-ylwx
 *
 */
public interface ILoginBiz {
	
	/**
	 * 登录
	 * @param username 用户名
	 * @param password 用户密码
	 * @param onLoginListener 回调事件
	 */
	public void login(String username, String password, OnLoginListener onLoginListener);
	
	/**
	 * 登出
	 * @param onLogoutListener
	 */
	public void logout(OnLogoutListener onLogoutListener);
}
