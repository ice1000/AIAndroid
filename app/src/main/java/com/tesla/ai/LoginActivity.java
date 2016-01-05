package com.tesla.ai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import util.tags;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toFloat();

        showTitle();

        new Thread(){

            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(
                        LoginActivity.this, MainActivity.class
                ));

                finish();
            }
        }.start();
    }

    private void toFloat(){
        ImageView NC = (ImageView) findViewById(R.id.ncFloating);
        NC.setBackgroundResource(R.drawable.nc_floating);
        ((AnimationDrawable) NC.getBackground()).start();
    }

    private void showTitle(){

        TextView title;
        SharedPreferences preferences;
        title = (TextView) findViewById(R.id.title);
        // 为了向下兼容，我使出了谜之表达式
        preferences = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                ? getSharedPreferences(tags.PREFERENCE_NAME, MODE_ENABLE_WRITE_AHEAD_LOGGING)
                : getSharedPreferences(tags.PREFERENCE_NAME, MODE_WORLD_READABLE);
        title.setText(preferences.getString(tags.OWNER_NAME,tags.MEET_FIRST_TIME));

        if(preferences.contains(tags.OWNER_NAME)){
            preferences.edit()
                    .putString(tags.OWNER_NAME, tags.HAVENT_GOT_NAME)
                    .apply();
        }

    }
}
