package com.tesla.ai;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        toFloat();
    }

    private void toFloat(){
        ImageView NC = (ImageView) findViewById(R.id.ncFloating);
        NC.setBackgroundResource(R.drawable.nc_floating);
        ((AnimationDrawable) NC.getBackground()).start();
        ImageView Saber = (ImageView) findViewById(R.id.saberShake);
        Saber.setBackgroundResource(R.drawable.saber_shake);
        ((AnimationDrawable) Saber.getBackground()).start();
    }

    public void gotoSettings(View view){
        startActivity(new Intent(
                AboutMeActivity.this, SettingsActivity.class
        ));
    }

}
