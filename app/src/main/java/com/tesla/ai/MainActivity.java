package com.tesla.ai;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import database.SQLiteManager;
import util.MyMessage;
import util.OnItemClickListener;
import util.TAGS;

public class MainActivity extends AppCompatActivity {

    private int nowBackgroundColor;
    private RecyclerView messageRecycler;
    private MessageAdapter adapter;
    private ArrayList<MyMessage> data;
    private EditText editMessage;
    private SQLiteManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
////            window.addFlags(
////                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.addFlags(
//                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(0xFFFFFFFF);
//        }

        editMessage = (EditText) findViewById(R.id.sendBox);

        manager = new SQLiteManager(this);
        data = manager.getMessages();

//        测试数据
//        data.add(new MyMessage(false, "宝贝再见~"));
//        data.add(new MyMessage(true, "亲爱的最喜欢了~"));

        adapter = new MessageAdapter();

        messageRecycler = (RecyclerView) findViewById(R.id.messagesRecycler);
        nowBackgroundColor = TAGS.BACKGROUND_COLOR_IS_0;

        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
        messageRecycler.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {}
            @Override
            public void onItemLongClick(View view, int position) {
                Intent intent = new Intent(
                        MainActivity.this, DeleteActivity.class
                );
                intent.putExtra(TAGS.POSITION, position);
                startActivityForResult(intent, TAGS.DELETE_REQUEST);
            }

            @Override
            public void onItemTouch(View view, int position, MotionEvent event) {

                boolean isFromSaber = data.get(position).isFromSaber();
                int[] colorId;

                Log.d(this.toString(),
                        "event.getAction() = " + event.getAction());

                switch (event.getAction()){
                    case 0:
                    case 1:
                    case 2:
                        colorId = isFromSaber ? new int[]{
                                R.color.cardColor1Pressed,
                                R.color.cardColor1
                        } : new int[]{
                                R.color.cardColor5Pressed,
                                R.color.cardColor5
                        };

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

                            int id = isFromSaber
                                    ? R.animator.background_color_1_to_5
                                    : R.animator.background_color_5_to_1;

                            // 如果现在的颜色已经正常了那就不用变化了
                            if(nowBackgroundColor == TAGS.BACKGROUND_COLOR_IS_5){
                                if(isFromSaber)
                                    break;
                                else
                                    nowBackgroundColor = TAGS.BACKGROUND_COLOR_IS_1;
                            }
                            else
                            if (nowBackgroundColor == TAGS.BACKGROUND_COLOR_IS_1){
                                if(!isFromSaber)
                                    break;
                                else
                                    nowBackgroundColor = TAGS.BACKGROUND_COLOR_IS_5;
                            }
                            else
                            if(nowBackgroundColor == TAGS.BACKGROUND_COLOR_IS_0)
                                nowBackgroundColor = isFromSaber
                                        ? TAGS.BACKGROUND_COLOR_IS_5
                                        : TAGS.BACKGROUND_COLOR_IS_1;

                            ObjectAnimator objectAnimator;

                            objectAnimator = (ObjectAnimator) AnimatorInflater
                                    .loadAnimator(MainActivity.this, id);

                            //用于动画计算的需要，
                            // 如果开始和结束的值不是基本类型的时候，这个方法是需要的。
                            objectAnimator.setEvaluator(new ArgbEvaluator());

                            //设置动画的设置目标
                            objectAnimator.setTarget(messageRecycler);
                            objectAnimator.start();
                        }
                        break;
                    default:

                        colorId = isFromSaber ? new int[]{
                                R.color.cardColor1,
                                R.color.cardColor1Pressed
                        } : new int[]{
                                R.color.cardColor5,
                                R.color.cardColor5Pressed
                        };

                        break;
                }

                ((CardView)((FrameLayout) view).getChildAt(0))
                        .setCardBackgroundColor(
                                getResources().getColor(colorId[0]));
                ((TextView)((CardView)((FrameLayout) view).getChildAt(0))
                        .getChildAt(0)).setTextColor(
                        getResources().getColor(colorId[1]));

            }
        });

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
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(
                        MainActivity.this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAGS.DELETE_REQUEST:
                switch (resultCode){
                    case DeleteActivity.resultCode:
                        if(data.getBooleanExtra(TAGS.DELETE_OR_NOT, false)){

                            // 因为我传递过去的时候是把position传递过去的
                            // 所以我猜再传递回来并且拿给adapter是没有问题的
                            deleteMessage(data.getIntExtra(TAGS.POSITION, 0));
                            Log.d(this.toString(), "data.getIntExtra(TAGS.POSITION, 0) = " +
                                    data.getIntExtra(TAGS.POSITION, 0));

                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

    }

//    public void gotoSettings(View view){
//        startActivity(new Intent(
//                MainActivity.this, SettingsActivity.class
//        ));
//    }

    public void commitMessage(View view){
        String msg = editMessage.getText().toString();

        // 去掉末尾换行符或者空格
        while (msg.endsWith("\n") || msg.endsWith(" ")){
            msg = msg.substring(0, msg.length()-1);
        }

        MyMessage message;
        message = new MyMessage(false, msg);
        data.add(message);
        adapter.notifyItemInserted(data.size()-1);
        manager.addMessage(message);
        answerMessage(msg);
        editMessage.setText("");
    }

    private void answerMessage(String msg){
        MyMessage message;
        message = new MyMessage(true, msg);
        data.add(message);
        adapter.notifyItemInserted(data.size()-1);
        manager.addMessage(message);
    }

    private void deleteMessage(int position){
        Log.d(this.toString(),
                "data.get(position) = " +
                data.get(position));

//        if(data.get(position).isIdAvailable()){
//            manager.deleteMessage(data.get(position));
//        }
//        else {
//            Log.d(
//                    MainActivity.this.toString(),
//                    TAGS.DELETE_FAILED
//            );
        manager.deleteMessageById(position);
//        }
        data.remove(position);
        adapter.notifyItemRemoved(position);
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


            if(message.isFromSaber()){
                params.gravity = Gravity.LEFT;
                params.setMargins(10,11,30,11);

                cardView.setCardBackgroundColor(
                        getResources().
                                getColor(R.color.cardColor1));
                textView.setTextColor(
                        getResources().
                                getColor(R.color.cardColor1Pressed));
            }

            else {
                params.gravity = Gravity.RIGHT;
                params.setMargins(30,11,10,11);

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
