package com.ylwx.mvp.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
	
	private static SharedPreferences sharedPreferences = null;
	private static SharedPreferencesUtil sharedPreferencesUtil = null;
	private static Editor editor = null;
	
	public SharedPreferencesUtil(Context context, String name){
		sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}
	
	/**
	 * 单例模式获取SharedPrefereces实例
	 * @param context
	 * @param name
	 * @return
	 * @date 创建时间：2016-5-9 下午12:10:13
	 */
	public static SharedPreferencesUtil getInstance(Context context, String name){
		
		if(sharedPreferencesUtil==null){
			sharedPreferencesUtil = new SharedPreferencesUtil(context, name);
		}
		
		return sharedPreferencesUtil;
	}
	
	/**
	 * 获取字符串数据
	 * @param key
	 * @param defValue
	 * @return
	 * @date 创建时间：2016-5-9 下午12:10:54
	 */
	public String getStringSharedPreferences(String key , String defValue){
		return sharedPreferences.getString(key, defValue);
	}
	
	/**
	 * 获取布尔数据
	 * @param key
	 * @param defValue
	 * @return
	 * @date 创建时间：2016-5-9 下午12:11:11
	 */
	public boolean getBooleanSharedPreferences(String key , Boolean defValue){
		return sharedPreferences.getBoolean(key, defValue);
	}
	
	/**
	 * 获取int数据
	 * @param key
	 * @param defValue
	 * @return
	 * @date 创建时间：2016-5-9 下午12:12:06
	 */
	public int getIntSharedPreferences(String key , int defValue){
		return sharedPreferences.getInt(key, defValue);
	}
	
	/**
	 * 获取long数据
	 * @param key
	 * @param defValue
	 * @return
	 * @date 创建时间：2016-5-9 下午12:12:52
	 */
	public long getLongSharedPreferences(String key , int defValue){
		return sharedPreferences.getLong(key, defValue);
	}
	
	/**
	 * 获取float数据
	 * @param key
	 * @param defValue
	 * @return
	 * @date 创建时间：2016-5-9 下午12:13:31
	 */
	public float getFloatSharedPreferences(String key , int defValue){
		return sharedPreferences.getFloat(key, defValue);
	}
	
	/**
	 * 保存字符串数据
	 * @param key
	 * @param value
	 * @return
	 * @date 创建时间：2016-5-9 下午12:10:54
	 */
	public void putStringSharedPreferences(String key , String value){
		editor.putString(key, value);
		editor.commit();
	}
	
	/**
	 * 保存布尔数据
	 * @param key
	 * @param value
	 * @return
	 * @date 创建时间：2016-5-9 下午12:11:11
	 */
	public void putBooleanSharedPreferences(String key , Boolean value){
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	/**
	 * 保存int数据
	 * @param key
	 * @param value
	 * @return
	 * @date 创建时间：2016-5-9 下午12:12:06
	 */
	public void putIntSharedPreferences(String key , int value){
		editor.putInt(key, value);
		editor.commit();
	}
	
	/**
	 * 保存long数据
	 * @param key
	 * @param defValue
	 * @return
	 * @date 创建时间：2016-5-9 下午12:12:52
	 */
	public void putLongSharedPreferences(String key , Long value){
		editor.putLong(key, value);
		editor.commit();
	}
	
	/**
	 * 保存float数据
	 * @param key
	 * @param defValue
	 * @return
	 * @date 创建时间：2016-5-9 下午12:13:31
	 */
	public void putFloatSharedPreferences(String key , int value){
		editor.putFloat(key, value);
		editor.commit();
	}
	
	/**
	 * 保存JSON数据 遍历保存 如果需要把所有数据保存到一个字段内
	 * 请转换成字符串保存
	 * @param jsonObject
	 * @date 创建时间：2016-5-9 下午2:02:36
	 */
	public void putJSONSharedPreferences(JSONObject jsonObject){
		
		Iterator<String>  keys = jsonObject.keys();
		String key = "";
		Object valueObject = null;
		while (keys.hasNext()) {
			try {
				key = keys.next();
				valueObject =  jsonObject.get(key);
				if(valueObject instanceof Integer){
					editor.putInt(key, Integer.parseInt(String.valueOf(valueObject)));
				}else if(valueObject instanceof String) {
					editor.putString(key, String.valueOf(valueObject));
				}else if(valueObject instanceof Long) {
					editor.putLong(key, Long.parseLong(String.valueOf(valueObject)));
				}else if(valueObject instanceof Float) {
						editor.putFloat(key, Float.parseFloat(String.valueOf(valueObject)));
				}else if(valueObject instanceof Boolean){ 
					editor.putBoolean(key, Boolean.parseBoolean(String.valueOf(valueObject)));
				}else{
					editor.putString(key, valueObject.toString());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		editor.commit();
	}
	
	/**
	 * 保存Map数据 只能存储基本数据类型 实体类将被调用toString转成字符串
	 * @param Map<String, Object> map 
	 * @date 创建时间：2016-5-9 下午1:41:50
	 */
	public static void putMapSharedPreferences(Map<String, Object> map){
		
		if(map!=null){
			Set<String> keys = map.keySet();
			Object valueObject = null;
			for (String key : keys) {
				valueObject =  map.get(key);
				if(valueObject instanceof Integer){
					editor.putInt(key, Integer.parseInt(String.valueOf(valueObject)));
//					System.out.println(key+","+ Integer.parseInt(String.valueOf(valueObject)));
				}else if(valueObject instanceof String) {
					editor.putString(key, String.valueOf(valueObject));
//					System.out.println(key+","+ String.valueOf(valueObject));
				}else if(valueObject instanceof Long) {
					editor.putLong(key, Long.parseLong(String.valueOf(valueObject)));
//					System.out.println(key+","+ Long.parseLong(String.valueOf(valueObject)));
				}else if(valueObject instanceof Float) {
						editor.putFloat(key, Float.parseFloat(String.valueOf(valueObject)));
//					System.out.println(key+","+ Float.parseFloat(String.valueOf(valueObject)));
				}else if(valueObject instanceof Boolean){ 
					editor.putBoolean(key, Boolean.parseBoolean(String.valueOf(valueObject)));
//					System.out.println(key+","+ Boolean.parseBoolean(String.valueOf(valueObject)));
				}else{
					editor.putString(key, valueObject.toString());
//					System.out.println(key+","+ valueObject.toString());
				}
			}
			editor.commit();
		}
	}
	
}
