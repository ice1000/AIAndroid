package com.tesla.ai;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        toFloat();
    }

    private void toFloat(){
        ImageView NC = (ImageView) findViewById(R.id.ncFloat2);
        NC.setBackgroundResource(R.drawable.nc_floating);

        AnimationDrawable NCFloat = (AnimationDrawable) NC.getBackground();
        NCFloat.start();
    }
}
