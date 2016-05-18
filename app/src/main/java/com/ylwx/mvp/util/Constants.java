package com.ylwx.mvp.util;

public class Constants {
	
	/**
	 * apk默认名称
	 */
	public static final String APKNAME = "ylwx.apk";
	
	/**
	 * 更新默认Json
	 */
	public static final String UPDATE_VERJSON = "update_json.json";
	
	/**
	 * 下载apk后保存文件名称前缀
	 */
	public static final String UPDATE_NAMEPREFIX = "ylwx_";
	
	/**
	 * 文件目录
	 */
	public static final String APP_DIR = "/ylwx";
	
	/**
	 * 启动服务action
	 */
	public static final String action_service = "com.jktx.ylwx.logic.YlwxService";
	/**
	 * （SharedPreferences key）是否为登陆状态
	 */
	public static final String isLogin = "isLogin";

	/**
	 *-任务ID
	 */
	public static final int TASK_ID = 10000;
	
	/**
	 * URL
	 */
	public static final String TASK_URL = "";
	
	/**
	 * HTTP请求错误代码
	 */
	public static final int ERROR_HTTP_CONNECT = -10000;
	
	/**
	 * HTTP读取错误代码
	 */
	public static final int ERROR_HTTP_READ = -10001;

	/**
	 * URL转换失败
	 */
	public static final int ERROR_HTTP_URL = -10002;

	/**
	 * HTTP链接超时
	 */
	public static final int ERROR_HTTP_TIMEOUT = -10003;

	/**
	 * HTTP读取超时
	 */
	public static final int ERROR_HTTP_READ_TIMEOUT = -10004;
	
}
