package com.ylwx.mvp.ui.login.interfaces;

import com.ylwx.mvp.entity.User;

/**
 * 用户登录View接口
 * @author wtt-ylwx
 *
 */
public interface ILoginView {
	
	/**
	 * 获取用户名
	 * @return
	 */
	public String getUserName();
	
	/**
	 * 获取密码
	 * @return 
	 */
	public String getPassWord();
	
	/**
	 * 显示加载
	 */
	public void showLoading();
	
	/**
	 * 隐藏加载
	 */
	public void hideLoading();
	
	/**
	 * 跳转到指定Activirty
	 * @param user 用户信息
	 */
	public void toTargetActivity(User user);
	
	/**
	 * 显示失败信息
	 */
	public void showFailedError();
	
	/**
	 * 清除UserName
	 */
	public void clearUserName();
	
	/**
	 * 清除密码
	 */
	public void clearPassWord();
	
}
