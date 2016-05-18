package com.ylwx.mvp.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylwx.mvp.R;

public class TitleBar extends FrameLayout {
	
	private RelativeLayout rl_titlebar;
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_title;
	
	public TitleBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titlebar);
		int textColor = typedArray.getColor(R.styleable.titlebar_textColor, R.color.font_color);
		int background = typedArray.getResourceId(R.styleable.titlebar_background, R.color.titlebar_bg);
		initView(context, textColor, background);
	}
	
	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titlebar);
		int textColor = typedArray.getColor(R.styleable.titlebar_textColor, R.color.font_color);
		
		int background = typedArray.getResourceId(R.styleable.titlebar_background, R.color.titlebar_bg);
		initView(context, textColor, background);
	}
	
	public void initView(Context context){
		initView(context, 0 , 0);
	}
	
	public void initView(Context context, int textColor, int background){
		if(isInEditMode())
			return;
		LayoutInflater.from(context).inflate(R.layout.view_titlebar, this);
		rl_titlebar = (RelativeLayout) findViewById(R.id.rl_titlebar);
		if(background!=0)
			rl_titlebar.setBackgroundResource(background);
		iv_left = (ImageView)findViewById(R.id.iv_left);
		iv_right = (ImageView)findViewById(R.id.iv_right);
		tv_title = (TextView)findViewById(R.id.tv_title);
		if(textColor!=0)
			tv_title.setTextColor(textColor);
	}
	
	/**
	 * 设置标题
	 * @param text
	 */
	public void setText(String text){
		tv_title.setText(text);
	}
	
	public void setLeftBackgroundResource(int resId){
		iv_left.setBackgroundResource(resId);
	}
	
	public void setRightBackgroundResource(int resId){
		iv_right.setBackgroundResource(resId);
	}
	
}
