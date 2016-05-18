package com.ylwx.mvp.http;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import com.ylwx.mvp.interfaces.Callback;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Http请求管理类
 * @author wtt-ylwx
 * @date 创建时间：2016-5-5 上午11:20:27
 */
public class HttpManager {
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static HttpManager httpManager;
	private OkHttpClient okHttpClient;
	
	private HttpManager(){
		if(okHttpClient==null){
			//创建okHttpClient对象
			okHttpClient = new OkHttpClient();
		}
	}
	
	/**
	 * 单例模式
	 * @return
	 * @date 创建时间：2016-5-5 上午10:08:29
	 */
	public static HttpManager getInstance(){
		if(httpManager==null){
			httpManager = new HttpManager();
		}
		return httpManager;
	}
	
	/**
	 * 异步Get请求
	 * @param url
	 * @param callback
	 * @date 创建时间：2016-5-5 上午10:07:28
	 */
	public void httpAsynGet(String url, final Callback callback){
		//创建一个Request
		final Request request = new Request.Builder()
		                .url(url)
		                .build();
		//new call
		Call call = okHttpClient.newCall(request); 
		//请求加入调度 异步加载 execute()阻塞式
		call.enqueue(new okhttp3.Callback(){

			@Override
			public void onFailure(Call call, IOException ioException) {
				// TODO Auto-generated method stub
				callback.onFailure(call, ioException);
			}

			/**
			 * 非UI线程
			 */
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				// TODO Auto-generated method stub
				callback.onResponse(call, response);
			}

        });             
		
	}
	
	/**
	 * 同步get请求
	 * @param url
	 * @return
	 * @throws IOException
	 * @date 创建时间：2016-5-5 上午10:06:52
	 */
	public String httpGet(String url) throws IOException{
		Request request = new Request.Builder().url(url).build();
		
		Call call = okHttpClient.newCall(request);
		Response response = call.execute();
		if (response.isSuccessful()) {
	        return response.body().string();
	    } else {
	        throw new IOException("Unexpected code " + response);
	    }
	}
	
	/**
	 * 
	 * 同步post请求json数据返回字符串数据
	 * @param url 服务器地址
	 * @param json 数据
	 * @return
	 * @throws IOException
	 * @date 创建时间：2016-5-5 上午10:05:02
	 */
	public String httpPost(String url, String json) throws IOException{
		RequestBody body = RequestBody.create(JSON, json);
		
	    Request request = new Request.Builder()
	    .url(url)
	    .post(body)
	    .build();

	    Response response = okHttpClient.newCall(request).execute();

	    if (response.isSuccessful()) {
	        return response.body().string();
	    } else {
	        throw new IOException("Unexpected code " + response);
	    }
	}
	
	/**
	 * 
	 * post同步请求
	 * @param url
	 * @param map key value
	 * @return
	 * @throws IOException
	 * @date 创建时间：2016-5-5 上午10:20:13
	 */
	public String httpPost(String url, Map<String, String> map) throws IOException{
		Builder builder = new FormBody.Builder();
		
		Set<String> keys = map.keySet();
		
		for (String key : keys) {
			builder.add(key, map.get(key));
		}
		
	    Request request = new Request.Builder()
	    .url(url)
	    .post(builder.build())
	    .build();

	    Response response = okHttpClient.newCall(request).execute();

	    if (response.isSuccessful()) {
	        return response.body().string();
	    } else {
	        throw new IOException("Unexpected code " + response);
	    }
	}
	
	/**
	 * post异步请求
	 * @param url
	 * @param json
	 * @param callback
	 * @throws IOException
	 * @date 创建时间：2016-5-5 上午10:27:06
	 */
	public void httpAsynPost(String url, String json, final Callback callback) throws IOException{
		RequestBody body = RequestBody.create(JSON, json);
		
	    Request request = new Request.Builder()
	    .url(url)
	    .post(body)
	    .build();

	    Call call = okHttpClient.newCall(request);
	    
	    call.enqueue(new okhttp3.Callback(){

			@Override
			public void onFailure(Call call, IOException ioException) {
				// TODO Auto-generated method stub
				callback.onFailure(call, ioException);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// TODO Auto-generated method stub
				callback.onResponse(call, response);
			}
	    	
	    });
	}
	
	/**
	 * post异步请求
	 * @param url
	 * @param map
	 * @param callback
	 * @throws IOException
	 * @date 创建时间：2016-5-5 上午10:28:35
	 */
	public void httpAsynPost(String url, Map<String, String> map, final Callback callback) throws IOException{
		Builder builder = new FormBody.Builder();
		
		Set<String> keys = map.keySet();
		
		for (String key : keys) {
			builder.add(key, map.get(key));
		}
		
	    Request request = new Request.Builder()
	    .url(url)
	    .post(builder.build())
	    .build();

	    Call call = okHttpClient.newCall(request);
	    
	    call.enqueue(new okhttp3.Callback(){

			@Override
			public void onFailure(Call call, IOException ioException) {
				// TODO Auto-generated method stub
				callback.onFailure(call, ioException);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// TODO Auto-generated method stub
				callback.onResponse(call, response);
			}
	    	
	    });
	}
	
	/**
	 * 同步上传文件
	 * @param url 上传url
	 * @param path 文件路径
	 * @return
	 * @date 创建时间：2016-5-10 下午5:19:09
	 */
	public String uploadFile(String url, String path){
		try {
			
			File file = new File(path);
			RequestBody body = new MultipartBody.Builder()
			    .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("media/type"), file))
			    .setType(MultipartBody.FORM)
			    .build();

			Request request = new Request.Builder()
			    .url("url")
			    .post(body)
			    .build();

			OkHttpClient client = new OkHttpClient();
			Response response = client.newCall(request).execute();
			  
			return response.body().string();
			  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "";
	}
	
}
