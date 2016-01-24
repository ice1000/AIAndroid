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

}
