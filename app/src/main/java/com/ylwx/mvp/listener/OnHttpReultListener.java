package com.ylwx.mvp.listener;

/**
 * Http请求响应事件
 * @author wtt-ylwx
 * @date 创建时间：2016-5-9 下午4:37:57
 */
public interface OnHttpReultListener {
	
	/**
	 * 成功返回数据
	 * @param data
	 * @date 创建时间：2016-5-9 下午4:38:16
	 */
	void OnSuccess(String data);
	
	/**
	 * 连接服务器失败
	 * @param error
	 * @date 创建时间：2016-5-9 下午4:38:36
	 */
	void OnFailed(String error);
}
