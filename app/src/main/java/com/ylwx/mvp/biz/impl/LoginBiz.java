package com.ylwx.mvp.biz.impl;

import com.ylwx.mvp.biz.ILoginBiz;
import com.ylwx.mvp.entity.User;
import com.ylwx.mvp.listener.OnLoginListener;
import com.ylwx.mvp.listener.OnLogoutListener;

/**
 * 
 * @author wtt-ylwx
 * @date 创建时间：2016-5-9 下午4:37:13
 */
public class LoginBiz implements ILoginBiz {

	@Override
	public void login(final String username, final String password, final OnLoginListener onLoginListener) {
		// TODO Auto-generated method stub
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(username.equals("admin")&&password.equals("123")){
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					onLoginListener.loginSuccess(user);
				}else{
					onLoginListener.loginFailed();
				}
				
			}
			
		}.start();
	}

	@Override
	public void logout(OnLogoutListener onLogoutListener) {
		// TODO Auto-generated method stub
		
	}

}
