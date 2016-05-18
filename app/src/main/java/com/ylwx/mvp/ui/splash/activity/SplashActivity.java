package com.ylwx.mvp.ui.splash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.ylwx.mvp.R;
import com.ylwx.mvp.annotation.inject.ViewInject;
import com.ylwx.mvp.base.BaseActivity;
import com.ylwx.mvp.presenter.SplashPresenter;
import com.ylwx.mvp.ui.brows.BrowsActivity;
import com.ylwx.mvp.ui.login.activity.LoginActivity;
import com.ylwx.mvp.ui.splash.interfaces.ISplashView;

public class SplashActivity extends BaseActivity implements ISplashView{

	private SplashPresenter splashPresenter = new SplashPresenter(this);
	private String url = "";
	@ViewInject(id=R.id.fl_splash, click="viewClick") FrameLayout fl_splash;
	@ViewInject(id=R.id.iv_page, click="viewClick") ImageView iv_page;
	@ViewInject(id=R.id.btn_toMain, click="viewClick") Button btn_toMain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		splashPresenter.initView();
		splashPresenter.delayedToTarget();
		
	}
	
	@Override
	public boolean delayedToTarget(final int seconds) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			public void run() {
				btn_toMain.setText(seconds+"s");
				
				if(seconds==0&&!toTarget){
					toTarget(LoginActivity.class);
				}
				
			}
		});
		
		return toTarget;
	}

	@Override
	public void settingPagerImage(final String newsPager) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Picasso.with(SplashActivity.this).load(newsPager).into(iv_page);
			}
		});
	}

	@Override
	public void settingUrl(String url) {
		// TODO Auto-generated method stub
		this.url = url;
	}
	
	public void viewClick(View v){
		switch (v.getId()) {
		case R.id.btn_toMain:
			toTarget(LoginActivity.class);
			break;
		case R.id.iv_page:
			if(url!=null&&!url.equals("")){
				Intent intent = new Intent();
				intent.putExtra("url", url);
				toTarget(BrowsActivity.class, intent);
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if(keyCode==KeyEvent.KEYCODE_BACK){
			toTarget = true;
			finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
}	
