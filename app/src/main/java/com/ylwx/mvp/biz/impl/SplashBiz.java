package com.ylwx.mvp.biz.impl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import com.ylwx.mvp.biz.ISplashBiz;
import com.ylwx.mvp.http.HttpManager;
import com.ylwx.mvp.interfaces.Callback;
import com.ylwx.mvp.listener.OnHttpReultListener;
import com.ylwx.mvp.util.MVP;

/**
 * 
 * @author wtt-ylwx
 * @date 创建时间：2016-5-9 下午4:37:04
 */
public class SplashBiz implements ISplashBiz {

	@Override
	public void initData(final OnHttpReultListener onHttpReultListener) {
		// TODO Auto-generated method stub
		HttpManager.getInstance().httpAsynGet(MVP.HTTP, new Callback() {
			
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// TODO Auto-generated method stub
//				String result = response.body().string();
				final String result = "{\"newsPager\":\"http://img2.imgtn.bdimg.com/it/u=3201604544,263175732&fm=21&gp=0.jpg\",\"url\":\"http://www.bootcss.com/\"}";
				if(result!=null&&!result.equals("")){
					onHttpReultListener.OnSuccess(result);
				}
			}
			
			@Override
			public void onFailure(Call call, IOException ioException) {
				// TODO Auto-generated method stub
				onHttpReultListener.OnFailed(ioException.getMessage());
			}
		});
	}
}