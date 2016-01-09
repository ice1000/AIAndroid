package com.tesla.ai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import util.CONSTS;

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
        ImageView Saber = (ImageView) findViewById(R.id.saberShake);
        Saber.setBackgroundResource(R.drawable.saber_shake);
        ((AnimationDrawable) Saber.getBackground()).start();
    }

    private void showTitle(){

        TextView title;
        SharedPreferences preferences;

        title = (TextView) findViewById(R.id.meetTitle);
        // 为了向下兼容，我使出了谜之表达式
        preferences = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                ? getSharedPreferences(CONSTS.PREFERENCE_NAME, MODE_ENABLE_WRITE_AHEAD_LOGGING)
                : getSharedPreferences(CONSTS.PREFERENCE_NAME, MODE_WORLD_READABLE);

        String text;
        text = preferences.getString(CONSTS.USER_NAME,"") + CONSTS.WHAT_THE_FUCK;

        if(text.equals(CONSTS.HAVEN_T_GOT_NAME + CONSTS.WHAT_THE_FUCK)){
            text = CONSTS.HAVEN_T_GOT_NAME;
        }

        if(!preferences.contains(CONSTS.USER_NAME)){
            text = CONSTS.MEET_FIRST_TIME;

            SharedPreferences.Editor editor;
            editor = preferences.edit();
            editor.putString(CONSTS.USER_NAME, CONSTS.HAVEN_T_GOT_NAME);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                editor.apply();
            }
            else {
                Log.d(this.toString(),"editor.commit() = " + editor.commit());
            }
        }

        Log.d(this.toString(), "text = " + text);

        title.setText(text);

    }
}
