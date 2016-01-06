package com.tesla.ai;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView messageRecycler;
    private MessageAdapter adapter;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new MessageAdapter();

        data = new ArrayList<>();

        messageRecycler = (RecyclerView) findViewById(R.id.messagesRecycler);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
        messageRecycler.setItemAnimator(new DefaultItemAnimator());
        messageRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void gotoSettings(View view){
        startActivity(new Intent(
                MainActivity.this, SettingsActivity.class
        ));
    }


    class MessageAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MessageViewHolder(LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.message, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView textView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.messageCard);
            textView = (TextView) itemView.findViewById(R.id.messageText);
        }

        public void init(boolean saidBySaber){
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );

            if(saidBySaber){
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
                        RelativeLayout.TRUE);
                cardView.setCardBackgroundColor(
                        getResources().getColor(R.color.cardColor7));
                textView.setTextColor(
                        getResources().getColor(R.color.cardColor7Pressed));
            }
            else {
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
                        RelativeLayout.TRUE);
                cardView.setCardBackgroundColor(
                        getResources().getColor(R.color.cardColor5));
                textView.setTextColor(
                        getResources().getColor(R.color.cardColor5Pressed));
            }
            cardView.setLayoutParams(params);
        }
    }

}
