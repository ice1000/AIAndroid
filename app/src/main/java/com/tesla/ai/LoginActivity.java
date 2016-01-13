package com.tesla.ai;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import brain.Memories;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showTitle();

        doStunts();

        new Thread(){

            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(6000);
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

    private void doStunts(){
        final ImageView NC = (ImageView) findViewById(R.id.ncFloating);
        final ImageView Saber = (ImageView) findViewById(R.id.saberShake);
        final ImageView imageView = (ImageView) findViewById(R.id.roundFore);
        final TextView logoTextView = (TextView) findViewById(R.id.logoText);
        final TextView saberTextView = (TextView) findViewById(R.id.meetTitle);
        final CardView NCCard = (CardView) findViewById(R.id.NCCard);

        NC.setBackgroundResource(R.drawable.nc_floating);
        ((AnimationDrawable) NC.getBackground()).start();

        Saber.setBackgroundResource(R.drawable.saber_shake);
        ((AnimationDrawable) Saber.getBackground()).start();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.progress_turning);
        imageView.startAnimation(animation);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        NCCard.setVisibility(View.INVISIBLE);
                        logoTextView.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        Saber.setVisibility(View.VISIBLE);
                        saberTextView.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        };

        new Thread(){
            @Override
            public void run() {
                super.run();
                Message message;
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                message = new Message();
                message.what = 0;
                handler.sendMessage(message);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }.start();
    }

    private void showTitle(){

        Memories memories = new Memories(this);

        TextView title;
        title = (TextView) findViewById(R.id.meetTitle);

        String text = memories.getAppTitle();
        Log.d(this.toString(), "text = " + text);
        title.setText(text);

    }
}
