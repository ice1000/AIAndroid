package com.tesla.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import util.TAGS;

public class DeleteActivity extends AppCompatActivity {

    private Intent intent;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent data = getIntent();
        position = data.getIntExtra(TAGS.POSITION, 0);

        intent = new Intent(
                DeleteActivity.this, MainActivity.class);
        intent.putExtra(TAGS.POSITION, position);
    }

    public void sureToDelete(View view){
        intent.putExtra(TAGS.DELETE_OR_NOT, true);
        startActivity(intent);
    }

    public void cancelToDelete(View view){
        intent.putExtra(TAGS.DELETE_OR_NOT, false);
        startActivity(intent);
    }
}
