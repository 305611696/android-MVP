package com.ylwx.mvp.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import com.ylwx.mvp.util.Constants;
import com.ylwx.mvp.util.MVP;

public class HttpProvider {
	
	private int code = 0;
	private String result = "";
	
	/**
	 * 连接服务器获取返回数据
	 * @param spec
	 * @param params
	 * @return
	 */
	public int sendHttpRequst(String spec, String params){
		
		URL url = null;
		HttpURLConnection huc = null;
		try {
			url = new URL(MVP.HTTP+spec);
			System.out.println(MVP.HTTP+spec+params);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = Constants.ERROR_HTTP_URL;
		}
		
		if(url!=null){
			
			try {
				huc = (HttpURLConnection)url.openConnection();
				// 设置是否从httpUrlConnection读入，默认情况下是true; 
				huc.setDoInput(true);
				// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在 
				// http正文内，因此需要设为true, 默认情况下是false; 
				huc.setDoOutput(true);
				//设置链接超时时间
				huc.setConnectTimeout(10*1000);
				//设置读取超时时间
				huc.setReadTimeout(10*1000);
				//设置请求方式
				huc.setRequestMethod("POST");
				// 设定传送的内容类型是可序列化的java对象 
				// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException) 
				huc.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
				//设置是否为长连
				huc.setRequestProperty("Connection", "Keep-Alive");
				//设置参数长度
				huc.setRequestProperty("Content-length", String.valueOf(params.getBytes().length));
			} catch (IllegalArgumentException e){
				// TODO: handle exception
				e.printStackTrace();
				code = Constants.ERROR_HTTP_TIMEOUT;
			}catch (SocketTimeoutException e) {
				// TODO: handle exception
				code = Constants.ERROR_HTTP_READ_TIMEOUT;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				code = Constants.ERROR_HTTP_CONNECT;
			}
			
			OutputStream os = null;
			
			if(huc!=null){
				try {
					//连接服务器
					huc.connect();
					//获取输出流
					os = huc.getOutputStream();
					//提交数据流
					os.write(params.getBytes());
					//把缓冲区的数据强行输出
					os.flush();
					
					int responsCode = huc.getResponseCode();
					
					if(responsCode == HttpURLConnection.HTTP_OK){
						StringBuffer sb = new StringBuffer();
						String resultStr = "";
						BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream(),"UTF-8"));
						while ((resultStr = br.readLine())!=null) {
							sb.append(resultStr);
						}
						
						result = sb.toString();
					} else{
						code = Constants.ERROR_HTTP_CONNECT;
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					code = Constants.ERROR_HTTP_CONNECT;
				} finally {
					if(os!=null){
						try {
							os.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(huc!=null){
						huc.disconnect();
					}
				}
			}
		}
		
		return code;
		
	}
	
	
	/**
	 * 返回数据
	 * @return
	 */
	public String getResultStr(){
		return result;
	}
	
	/**
	 * 错误信息
	 * @param code
	 * @return
	 */
	public String getErrorStr(int code){
		
		switch (code) {
			case Constants.ERROR_HTTP_CONNECT:
				result = "连接服务器失败";
				break;
			case Constants.ERROR_HTTP_READ:
				result = "读取数据超时";
				break;
			case Constants.ERROR_HTTP_TIMEOUT:
				result = "连接服务器超时";
				break;
			case Constants.ERROR_HTTP_URL:
				result = "URL转换失败";
				break;
			default:
				result = "连接服务器失败";
				break;
		}
		
		return result;
	}
}
