package database;

import android.content.ContentValues;
import android.content.Context;
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

        return null;
    }

    public void addMessage(MyMessage myMessage){
        ContentValues contentValues = new ContentValues();
        contentValues.put("msg", myMessage.getMessage());

        database.insert(SQLiteOpener.TALK_LOG_TABLE, null, contentValues);
    }


}
