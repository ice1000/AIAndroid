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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import database.SQLiteManager;
import util.MyMessage;
import util.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView messageRecycler;
    private MessageAdapter adapter;
    private ArrayList<MyMessage> data;
    private EditText editMessage;
    private SQLiteManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editMessage = (EditText) findViewById(R.id.sendBox);

        manager = new SQLiteManager(this);
        data = manager.getMessage();
        data.add(new MyMessage(false, "宝贝再见~"));
        data.add(new MyMessage(true, "亲爱的最喜欢了~"));

        adapter = new MessageAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {}
            @Override
            public void onItemLongClick(View view, int position) {
                startActivity(new Intent(
                        MainActivity.this, DeleteActivity.class));
            }

            @Override
            public void onItemTouch(View view, int position, MotionEvent event) {

            }
        });

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

    public void commitMessage(View view){
        String msg = editMessage.getText().toString();
        MyMessage message;
        message = new MyMessage(false, msg);
        data.add(message);
        adapter.notifyItemInserted(data.size()-1);
        manager.addMessage(message);
        answerMessage(msg);
    }

    private void answerMessage(String msg){
        MyMessage message;
        message = new MyMessage(true, msg);
        data.add(message);
        adapter.notifyItemInserted(data.size()-1);
        manager.addMessage(message);
    }

    class MessageAdapter extends RecyclerView.Adapter {

        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(
                OnItemClickListener onItemClickListener){
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MessageViewHolder(LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.message, parent, false));
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

            ((MessageViewHolder)holder).init(data.get(position));

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(
                        new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(holder.itemView,
                                holder.getLayoutPosition());
                    }
                });

                holder.itemView.setOnLongClickListener(
                        new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.onItemLongClick(holder.itemView,
                                holder.getLayoutPosition());
                        return false;
                    }
                });

                holder.itemView.setOnTouchListener(
                        new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        onItemClickListener.onItemTouch(holder.itemView,
                                holder.getLayoutPosition(), event);
                        return false;
                    }
                });
            }
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
            cardView = (CardView)
                    itemView.findViewById(R.id.messageCard);
            textView = (TextView)
                    itemView.findViewById(R.id.messageText);
        }

        public void init(MyMessage message){
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );

            params.setMargins(10,11,10,11);

            if(message.isFromSaber()){
                params.gravity = Gravity.LEFT;
                cardView.setCardBackgroundColor(
                        getResources().
                                getColor(R.color.cardColor1));
                textView.setTextColor(
                        getResources().
                                getColor(R.color.cardColor1Pressed));
            }

            else {
                params.gravity = Gravity.RIGHT;
                cardView.setCardBackgroundColor(
                        getResources().
                                getColor(R.color.cardColor5));
                textView.setTextColor(
                        getResources().
                                getColor(R.color.cardColor5Pressed));
            }

            cardView.setLayoutParams(params);
            textView.setText(message.getMessage());
        }
    }

}
