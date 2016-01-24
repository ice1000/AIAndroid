package com.tesla.ai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import util.BaseActivity;

public class DeleteActivity extends Activity {

    public static final int resultCode = 666;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent data = getIntent();
        intent = new Intent();
        intent.putExtra(
                BaseActivity.T.POSITION,
                data.getIntExtra(BaseActivity.T.POSITION, 0)
        );
    }

    public void sureToDelete(View view){
        intent.putExtra(BaseActivity.T.DELETE_OR_NOT, true);
        setResult(resultCode,intent);
        finish();
    }

    public void cancelToDelete(View view){
        intent.putExtra(BaseActivity.T.DELETE_OR_NOT, false);
        setResult(resultCode,intent);
        finish();
    }
}
