package com.ylwx.mvp.presenter;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import com.ylwx.mvp.biz.impl.SplashBiz;
import com.ylwx.mvp.listener.OnHttpReultListener;
import com.ylwx.mvp.ui.splash.interfaces.ISplashView;
import com.ylwx.mvp.util.SharedPreferencesUtil;

/**
 * 逻辑处理
 * @author wtt-ylwx
 * @date 创建时间：2016-5-9 下午4:38:55
 */
public class SplashPresenter {
	
	//数据存取
	private SharedPreferencesUtil sharedPreferencesUtil;
	ISplashView splashView = null;
	SplashBiz splashBiz = null;
	Context context;
	
	public SplashPresenter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.splashView = (ISplashView)context;
		splashBiz = new SplashBiz();
	}
	
	/**
	 * 初始化默认View
	 * 
	 * @date 创建时间：2016-5-9 下午3:26:11
	 */
	public void initDefView(){
		sharedPreferencesUtil = SharedPreferencesUtil.getInstance(context, "splash");
		String newsPager = sharedPreferencesUtil.getStringSharedPreferences("newsPager", "http://img4.imgtn.bdimg.com/it/u=2554692961,1410875637&fm=21&gp=0.jpg");
		String url = sharedPreferencesUtil.getStringSharedPreferences("url", "http://www.bootcss.com/");
		
		splashView.settingPagerImage(newsPager);
		splashView.settingUrl(url);
	}
	
	/**
	 * 初始化View
	 * 
	 * @date 创建时间：2016-5-9 下午3:24:34
	 */
	public void initView(){
		
		initDefView();
		
		splashBiz.initData(new OnHttpReultListener() {
			
			@Override
			public void OnSuccess(String data) {
				// TODO Auto-generated method stub
				try {
					final JSONObject jsonObject = new JSONObject(data);
					//加载广告页面
					//返回值 图片imgPath 跳转url
					splashView.settingPagerImage(jsonObject.getString("newsPager"));
					splashView.settingUrl(jsonObject.getString("url"));
					//保存返回的JSON数据
					sharedPreferencesUtil.putJSONSharedPreferences(jsonObject);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void OnFailed(String error) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	/**
	 * 延时跳转
	 * 
	 * @date 创建时间：2016-5-9 下午3:24:46
	 */
	public void delayedToTarget(){
		new Thread(){
			
			int seconds = 11;
			
			@Override
			public void run() {
				
				do{
					seconds--;
					boolean toTarget = splashView.delayedToTarget(seconds);
					if(toTarget){
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}while(seconds>=0);
			}
			
		}.start();
	}
	
}
