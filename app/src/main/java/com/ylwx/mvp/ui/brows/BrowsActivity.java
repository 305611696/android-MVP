package com.ylwx.mvp.ui.brows;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylwx.mvp.R;
import com.ylwx.mvp.annotation.inject.ViewInject;
import com.ylwx.mvp.base.BaseActivity;

public class BrowsActivity extends BaseActivity{

	@ViewInject(id=R.id.iv_left, click="viewClick") ImageView iv_left;
	@ViewInject(id=R.id.iv_right, click="viewClick") ImageView iv_right;
	@ViewInject(id=R.id.tv_title)TextView tv_title;
	@ViewInject(id=R.id.web_view) WebView web_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brows);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		iv_left.setBackgroundResource(R.drawable.ic_launcher);
		tv_title.setText(url);
		
		WebSettings webSettings = web_view.getSettings();
		webSettings.setJavaScriptEnabled(true);
		web_view.setWebChromeClient(new MyWebChromeClient());
		web_view.setWebViewClient(new MyWebViewClient());
		web_view.loadUrl(url);
	}
	
	class MyWebChromeClient extends WebChromeClient{

		@Override  
        public void onProgressChanged(WebView view, int newProgress) {  
           /* mActivity.setProgress(newProgress * 1000);  */
        }  
  
        @Override  
        public void onReceivedTitle(WebView view, String title) {  
            // TODO Auto-generated method stub  
            super.onReceivedTitle(view, title);  
        }  
  
        @Override  
        public void onReceivedTouchIconUrl(WebView view, String url,  
                boolean precomposed) {  
            // TODO Auto-generated method stub  
            super.onReceivedTouchIconUrl(view, url, precomposed);  
        }  
  
        @Override  
        public boolean onCreateWindow(WebView view, boolean isDialog,  
                boolean isUserGesture, Message resultMsg) {  
            // TODO Auto-generated method stub  
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);  
        }  
        
	}
	
	class MyWebViewClient extends WebViewClient{

		@Override  
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
            // TODO Auto-generated method stub  
            return super.shouldOverrideUrlLoading(view, url);  
        }  
  
        @Override  
        public void onPageStarted(WebView view, String url, Bitmap favicon) {  
            // TODO Auto-generated method stub  
            super.onPageStarted(view, url, favicon);  
        }  
  
        @Override  
        public void onPageFinished(WebView view, String url) {  
            // TODO Auto-generated method stub  
            super.onPageFinished(view, url);  
        }  
  
        @Override  
        public void onReceivedError(WebView view, int errorCode,  
                String description, String failingUrl) {  
            // TODO Auto-generated method stub  
            super.onReceivedError(view, errorCode, description, failingUrl);  
        }  
  
        @Override  
        public void doUpdateVisitedHistory(WebView view, String url,  
                boolean isReload) {  
            // TODO Auto-generated method stub  
            super.doUpdateVisitedHistory(view, url, isReload);  
        }  
	}
	
	public void viewClick(View v){
		switch (v.getId()) {
		case R.id.iv_left:
			finish();
			break;

		default:
			break;
		}
	}
	
	//改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(web_view.canGoBack())
            {
            	web_view.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
	
}
