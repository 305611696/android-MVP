package com.ylwx.mvp.interfaces;

/**
 * 
 * @author wtt-ylwx
 * @date 创建时间：2016-5-9 下午4:37:45
 */
public interface Callback{
		
		/**
		 * 请求失败 
		 * @param call
		 * @param ioException
		 * @date 创建时间：2016-5-5 上午10:09:17
		 */
		public void onFailure(okhttp3.Call call, java.io.IOException ioException);
		  
		/**
		 * 	//返回的字符串
		 *	String htmlStr =  response.body().string();
		 *	//返回的二进制字节数组
		 *	response.body().bytes()
		 *	//返回的inputStream
		 *	response.body().byteStream()
		 *	runOnUiThread(new Runnable() {
		 *		@Override
		 *		public void run() {
		 *				    
		 *		}
		 *	});
		 * @param call
		 * @param response
		 * @throws java.io.IOException
		 * @date 创建时间：2016-5-5 上午10:03:12
		 */
		public void onResponse(okhttp3.Call call, okhttp3.Response response) throws java.io.IOException;
	}