package com.tesla.ai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import brain.Memories;

public class RenameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename);
    }

    public void sureToRename(View view){
        EditText editText = (EditText) findViewById(R.id.nameEditor);
        String name = editText.getText().toString();

        Memories memories = new Memories(this);
        memories.putName(name);

        Toast.makeText(
                RenameActivity.this,
                "好的~今后我就叫"+name+"啦~",
                Toast.LENGTH_SHORT
        ).show();
        finish();
    }

    public void cancelToRename(View view){
        finish();
    }
}
