package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import util.MyMessage;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class SQLiteManager {

    private SQLiteOpener opener;
    private SQLiteDatabase database;

    public SQLiteManager(Context context) {
        opener = new SQLiteOpener(context);
        database = opener.getWritableDatabase();
    }

    public ArrayList<MyMessage> getMessage(){

        ArrayList<MyMessage> messages = new ArrayList<>();
        Cursor cursor = database.query(
                SQLiteOpener.TALK_LOG_TABLE, null, null, null, null, null,
                MyMessage.ID, null);

        cursor.moveToLast();

        do {
            MyMessage myMessage;
            myMessage = new MyMessage(
                    cursor.getInt(cursor.getColumnCount()),
                    cursor.getString(cursor.getColumnCount())
            );
        } while (cursor.moveToPrevious());

        cursor.close();

        return messages;
    }

    public void addMessage(MyMessage myMessage){

        ContentValues contentValues = new ContentValues();
        contentValues.put("msg", myMessage.getMessage());
        contentValues.put("fromSaber", myMessage.isFromSaber());

        database.insert(SQLiteOpener.TALK_LOG_TABLE, null, contentValues);
    }

}
