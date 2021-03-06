package com.ylwx.mvp.ui.login.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.ylwx.mvp.R;
import com.ylwx.mvp.annotation.inject.OnClick;
import com.ylwx.mvp.annotation.inject.ViewInject;
import com.ylwx.mvp.base.BaseActivity;
import com.ylwx.mvp.entity.User;
import com.ylwx.mvp.presenter.LoginPresenter;
import com.ylwx.mvp.ui.login.interfaces.ILoginView;
import com.ylwx.mvp.ui.view.CustomToast;

public class LoginActivity extends BaseActivity implements ILoginView{

	@ViewInject(id=R.id.et_username) EditText et_username;
	@ViewInject(id=R.id.et_password) EditText et_password;
	@ViewInject(id=R.id.btn_test, click="viewClick", longClick="viewLongClick") Button btn_test;
	
	LoginPresenter userLoginPresenter = new LoginPresenter(this);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        et_username.setText("admin");
        et_username.setSelection(et_username.getText().length());
		Log.d("LoginActivity", "Init Login View");

	}

    public void viewLongClick(View v){
    	System.out.println("viewLongClick："+v.getId());
    }
    
    public void viewTouch(View v, MotionEvent event){
    	System.out.println("viewTouch："+v.getId()+","+event.getAction());
    }
    
    public void viewClick(View v){
    	System.out.println("viewClick："+v.getId());
    	userLoginPresenter.login();
		Log.d("LoginActivity", "login");
    }
    
    @OnClick(id = {R.id.et_username})
    public void onClick(View v){
    	System.out.println("onClick："+v.getId());
    }
    
	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return et_username.getText().toString();
	}

	@Override
	public String getPassWord() {
		// TODO Auto-generated method stub
		return et_password.getText().toString();
	}

	@Override
	public void showLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toTargetActivity(final User user) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				CustomToast.showToast(LoginActivity.this, new Gson().toJson(user), 1000);
			}
		});
	}

	@Override
	public void showFailedError() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				CustomToast.showToast(LoginActivity.this, "登录失败", 1000);
			}
		});

	}

	@Override
	public void clearUserName() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearPassWord() {
		// TODO Auto-generated method stub
		
	}
}
