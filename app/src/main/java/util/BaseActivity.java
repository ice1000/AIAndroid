package util;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.tesla.ai.R;
import com.tesla.ai.SettingsActivity;

/**
 * 封装的基类
 * Copyright 2016(c) Comet Corporation.
 * Created by asus1 on 2016/1/23.
 */
public class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * 初始化ToolBar
	 * @return 可能还会对ToolBar有一些操作
	 */
	protected Toolbar initToolBar(){
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		return toolbar;
	}

	/**
	 * 播放一个逐帧动画。
	 * @param id1 View的ID
	 * @param id2 逐帧动画的ID
	 */
	protected void playFrameAnimation(@IdRes int id1, @DrawableRes int id2){
		ImageView Saber = (ImageView) findViewById(id1);
		Saber.setBackgroundResource(id2);
		((AnimationDrawable) Saber.getBackground()).start();
	}

	/**
	 * 播放一个补间动画。
	 * @param id1 View的ID
	 * @param id2 补间动画的ID
	 */
	protected void playTweenAnimation(@IdRes int id1, @AnimRes int id2){
		findViewById(id1).startAnimation(
			AnimationUtils.loadAnimation(this, id2)
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_other, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.action_settings:
				startActivity(new Intent(
						this, SettingsActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * 只保留了Activity中需要的
	 * Created by Administrator on 2016/1/5 0005.
	 */
	public static class T {

	    public static final String PREFERENCE_NAME = "PREFERENCE_NAME";
	    public static final String SABER_NAME = "SABER_NAME";
	    public static final String HAVEN_T_GOT_NAME = "快给我起个名字\n\\(≧▽≦)/";
	    public static final String WHAT_THE_FUCK = "好想念主人啊~";
	    public static final String POSITION = "POSITION";
	    public static final String DELETE_OR_NOT = "DELETE_OR_NOT";
	    public static final String SORRY_CANNOT_JOIN =  "由于开发者害怕被肛，暂时无法联系上他。百度ID @精灵谱尼 哦";

		// 在MainActivity中的
	    public static final int DONT_NEED_THIS_PARAM  = -1;
	    // 数字都是随手打的，记住绝对不能一样！
	    public static final int DELETE_REQUEST        = 1;
	    public static final int BACKGROUND_COLOR_IS_5 = 2;
	    public static final int BACKGROUND_COLOR_IS_1 = 3;
	    public static final int BACKGROUND_COLOR_IS_0 = 4;
	    // 在SettingsActivity中的
	    public static final int HDF_STUDIO = 5;
	    public static final int PROGRAM_LEAGUE = 6;

	    // 在主脑中的
	    public static final int ANSWER_MESSAGE_SENT = 7;
	    public static final int ANSWER_MESSAGE_DELETED = 8;
	    public static final int WHOLE_DATASET_CHANGED = 9;
	    public static final int ANSWER_MESSAGE_RECIEVED = 10;

	    // 还有几个在MyMessage中
	}
}
