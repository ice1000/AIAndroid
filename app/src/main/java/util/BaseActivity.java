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
 * Copyright 2016(c) Comet Corporation.
 * Created by asus1 on 2016/1/23.
 */
public class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected Toolbar initToolBar(){
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		return toolbar;
	}

	protected void playFrameAnimation(@IdRes int id1, @DrawableRes int id2){
		ImageView Saber = (ImageView) findViewById(id1);
		Saber.setBackgroundResource(id2);
		((AnimationDrawable) Saber.getBackground()).start();
	}

	protected void playTweenAnimation(@IdRes int id1, @AnimRes int id2){
		findViewById(id1).startAnimation(
			AnimationUtils.loadAnimation(
				this, id2
			)
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
	 * Created by Administrator on 2016/1/5 0005.
	 */
	public static class T {

	    public static final String PREFERENCE_NAME = "PREFERENCE_NAME";
	    public static final String SABER_NAME = "SABER_NAME";
	    public static final String MEET_FIRST_TIME = "第一次见，先生您好~";
	    public static final String HAVEN_T_GOT_NAME = "快给我起个名字\n\\(≧▽≦)/";
	    public static final String HELLO_HONEY = "嗨~";
	    public static final String WHAT_THE_FUCK = "好想念主人啊~";
	    public static final String POSITION = "POSITION";
	    public static final String DELETE_OR_NOT = "DELETE_OR_NOT";
	    public static final String DELETE_FAILED = "删除失败，使用暴力方法删除";
	    public static final String SORRY_CANNOT_USE =  "抱歉本功能暂未开放";
	    public static final String SORRY_CANNOT_JOIN =  "由于开发者害怕被肛，暂时无法联系上他。百度ID @精灵谱尼 哦";

		public static final String NO_MESSAGE_FOUND =  "没有找到消息";

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
