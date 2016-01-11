package com.tesla.ai;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import brain.Memories;

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

        Memories memories = new Memories(this);

        TextView title;
        title = (TextView) findViewById(R.id.meetTitle);

        String text = memories.getName();
        Log.d(this.toString(), "text = " + text);
        title.setText(text);

    }
}
