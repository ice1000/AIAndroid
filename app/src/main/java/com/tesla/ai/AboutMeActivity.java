package com.tesla.ai;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import util.BaseActivity;

public class AboutMeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        initToolBar();

        playFrameAnimation(
                R.id.ncFloating,
                R.drawable.nc_floating
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
