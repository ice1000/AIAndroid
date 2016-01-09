package brain;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import database.SQLiteManager;
import util.BrainUsingActivity;
import util.CONSTS;
import util.MyMessage;

/**
 * Copyright 2016(c) Comet Corporation.
 * Created by asus1 on 2016/1/9.
 */
public class MainBrain {

    private SQLiteManager manager;
    private ArrayList<MyMessage> data;
    private String lastGivenMessage;
    private BrainUsingActivity activity;

    public MainBrain(Context context) {
        manager = new SQLiteManager(context);
        data = manager.getMessages();
        activity = (BrainUsingActivity) context;
    }

    public void giveMessage(String Message) {
        this.lastGivenMessage = Message;
        activity.notifyAdapter(data.size()-1, CONSTS.ANSWER_MESSAGE_RECIEVED);
        handleLastGivenMessage();
    }

    public void deleteMessage(int position){
        Log.d(this.toString(),
                "data.get(position) = " +
                data.get(position).getMessage()
        );
//        if(data.get(position).isIdAvailable()){
//            manager.deleteMessage(data.get(position));
//        }
//        else {
//            Log.d(
//                    MainActivity.this.toString(),
//                    CONSTS.DELETE_FAILED
//            );
        manager.deleteMessageById(
                data.get(position).getId()
        );
//        }
        data.remove(position);

        activity.notifyAdapter(
                position,
                CONSTS.ANSWER_MESSAGE_DELETED
        );
    }

    private void handleLastGivenMessage(){
        // 去掉首尾换行符或者空格
        while (lastGivenMessage.endsWith("\n") ||
                lastGivenMessage.endsWith(" ")){
            lastGivenMessage = lastGivenMessage.
                    substring(0, lastGivenMessage.length()-1);
        }
        while (lastGivenMessage.startsWith("\n") ||
                lastGivenMessage.startsWith(" ")){
            lastGivenMessage = lastGivenMessage.
                    substring(1, lastGivenMessage.length());
        }
        MyMessage message;
        message = new MyMessage(
                false,
                lastGivenMessage
        );
        manager.addMessage(message);
        data.add(manager.getLastMessage());

        ArrayList<String> answerWhichIsReadyToBeSent = new ArrayList<>();
        int cnt = 0;
        for (String shouldBeSplit : CONSTS.SHOULD_BE_SPLIT ) {
            if (lastGivenMessage.contains(shouldBeSplit)) {
                cnt++;
                String[] lastGivenMessages = lastGivenMessage.split(shouldBeSplit);
                Collections.addAll(answerWhichIsReadyToBeSent, lastGivenMessages);
            }
        }
        Log.d(toString(), "cnt = " + cnt);
        if(cnt == 0)
            answerWhichIsReadyToBeSent.add(lastGivenMessage);

        sendAnswerAsMessage(answerWhichIsReadyToBeSent);
    }

    private void sendAnswerAsMessage(ArrayList<String> answerMessage){
        for (String msg : answerMessage) {
            MyMessage message = new MyMessage(true, msg, data.size()-1);
            data.add(message);
            manager.addMessage(message);
            activity.notifyAdapter(
                    data.size()-1,
                    CONSTS.ANSWER_MESSAGE_SENT
            );
        }
    }

    public ArrayList<MyMessage> getData() {
        return data;
    }

    public int getDataSize() {
        return data.size();
    }
    
    public void refreshData(){
        data.clear();
        data = manager.getMessages();
        activity.notifyAdapter(
                CONSTS.DONT_NEED_THIS_PARAM,
                CONSTS.WHOLE_DATASET_CHANGED
        );
    }
    
    public void clearData(){
        manager.removeAll();
        data.clear();
        activity.notifyAdapter(
                CONSTS.DONT_NEED_THIS_PARAM,
                CONSTS.WHOLE_DATASET_CHANGED
        );
    }

    public MyMessage getMessageByPosition(int position){
        return data.get(position);
    }

    public boolean isDataEmpty(){
        return data.isEmpty();
    }
}
