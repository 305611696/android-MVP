package com.ylwx.mvp.presenter;

import com.ylwx.mvp.biz.impl.LoginBiz;
import com.ylwx.mvp.entity.User;
import com.ylwx.mvp.listener.OnLoginListener;
import com.ylwx.mvp.ui.login.interfaces.ILoginView;

/**
 * 负责逻辑的处理
 * @author wtt-ylwx
 * @date 创建时间：2016-5-5 上午8:41:39
 */
public class LoginPresenter {
	
	LoginBiz userBiz = null;
	ILoginView iLoginView = null;

	/**
	 * 构造
	 * @param iLoginView
	 */
	public LoginPresenter(ILoginView iLoginView) {
		// TODO Auto-generated constructor stub
		this.iLoginView = iLoginView;
		userBiz = new LoginBiz();
	}
	
	/**
	 * 登录操作
	 * 
	 * @date 创建时间：2016-5-5 上午8:43:55
	 */
	public void login(){
		iLoginView.showLoading();
		userBiz.login(iLoginView.getUserName(), iLoginView.getPassWord(), new OnLoginListener() {
			
			@Override
			public void loginSuccess(User user) {
				// TODO Auto-generated method stub
				iLoginView.toTargetActivity(user);
				iLoginView.hideLoading();
			}
			
			@Override
			public void loginFailed() {
				// TODO Auto-generated method stub
				iLoginView.showFailedError();
				iLoginView.hideLoading();
			}
		});
	}
	
	/**
	 * 清除文本框内容
	 * 
	 * @date 创建时间：2016-5-5 上午8:44:11
	 */
	void clear(){
		iLoginView.clearUserName();
		iLoginView.clearPassWord();
	}
	
}
