package com.ylwx.mvp.listener;

import com.ylwx.mvp.entity.User;

/**
 * 登录事件 
 * @author wtt-ylwx
 *
 */
public interface OnLoginListener {
	/**
	 * 登录成功
	 * @param user 登录用户信息
	 */
	public void loginSuccess(User user);
	
	/**
	 * 登录失败
	 */
	public void loginFailed();
}
