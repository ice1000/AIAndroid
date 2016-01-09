package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import util.CONSTS;
import util.MyMessage;

/**
 * Copyright 2016(c) Comet Corporation.
 * Created by Administrator on 2016/1/6 0006.
 */
public class SQLiteManager {

    private SQLiteDatabase database;

    public SQLiteManager(Context context) {
        SQLiteOpener opener = new SQLiteOpener(context);
        database = opener.getWritableDatabase();
    }

    public ArrayList<MyMessage> getMessages(){

        ArrayList<MyMessage> messages = new ArrayList<>();
        Cursor cursor = database.query(
                CONSTS.TALK_LOG_TABLE,
//                new String[]{
//                        MyMessage.FROM_SABER,
//                        MyMessage.MSG
//                },
                null,
                null,
                null,
                null,
                null,
                MyMessage.ID
        );

        cursor.moveToFirst();

        // 避免什么都没有的情况
        if(cursor.isAfterLast()){
            return new ArrayList<>();
        }

        Log.d(toString(), "in the beginning, cursor.getInt(cursor.getColumnIndex(MyMessage.ID)) = " +
                cursor.getInt(cursor.getColumnIndex(MyMessage.ID)));

        if(!cursor.isBeforeFirst() && !cursor.isAfterLast()){
            do {
                messages.add(new MyMessage(
                        cursor.getInt(cursor.getColumnIndex(MyMessage.FROM_SABER)),
                        cursor.getString(cursor.getColumnIndex(MyMessage.MSG)),
                        cursor.getInt(cursor.getColumnIndex(MyMessage.ID))
                ));

                Log.d(toString(), "cursor.getInt(cursor.getColumnIndex(MyMessage.ID)) = " +
                        cursor.getInt(cursor.getColumnIndex(MyMessage.ID)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return messages;
    }

    public void addMessage(MyMessage myMessage){

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyMessage.MSG, myMessage.getMessage());

        // 因为SQLite没有boolean类型所以在存取的时候转换成整数
        contentValues.put(MyMessage.FROM_SABER, myMessage.isFromSaber()
                ? MyMessage.IS_FROM_SABER : 0);

        database.insert(CONSTS.TALK_LOG_TABLE,
                null, contentValues);
    }

    public MyMessage getLastMessage(){
        Cursor cursor = database.query(
                CONSTS.TALK_LOG_TABLE,
                null,
                null,
                null,
                null,
                null,
                MyMessage.ID
        );
        cursor.moveToLast();
        return new MyMessage(
                cursor.getInt(cursor.getColumnIndex(MyMessage.FROM_SABER)),
                cursor.getString(cursor.getColumnIndex(MyMessage.MSG)),
                cursor.getInt(cursor.getColumnIndex(MyMessage.ID))
        );
    }

    public void deleteMessage(MyMessage message){
        database.delete(
                CONSTS.TALK_LOG_TABLE,
                MyMessage.ID + "=" + message.getId(),
                null
        );
    }

    public MyMessage getOneMessage(int id){
        Cursor cursor = database.rawQuery(
                "select * from " + CONSTS.TALK_LOG_TABLE + " where " + MyMessage.ID + "=" + id,
                null
        );
        cursor.moveToFirst();
        return new MyMessage(
                cursor.getInt(cursor.getColumnIndex(MyMessage.FROM_SABER)),
                cursor.getString(cursor.getColumnIndex(MyMessage.MSG)),
                cursor.getInt(cursor.getColumnIndex(MyMessage.ID))
        );
    }

    public void deleteMessageById(int id) {
        Log.d(toString(), "id = " + id);
//        MyMessage message = getOneMessage(id);
//        Log.d(toString(),
//                "message.getId() = " + message.getId() +
//                ", id = " + id
//        );
        database.delete(
                CONSTS.TALK_LOG_TABLE,
                MyMessage.ID + "=" + id,
                null
        );
    }

    public void removeAll(){
        database.execSQL(
                "DELETE FROM " + CONSTS.TALK_LOG_TABLE
        );
    }
}
