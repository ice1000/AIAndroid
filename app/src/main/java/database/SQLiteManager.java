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
                SQLiteOpener.TALK_LOG_TABLE,
//                new String[]{
//                        MyMessage.FROM_SABER,
//                        MyMessage.MSG
//                },
                null,

                null, null, null, null,
                MyMessage.ID
        );

        cursor.moveToFirst();
        if(!cursor.isBeforeFirst() && !cursor.isAfterLast()){
            do {
                messages.add(new MyMessage(
                        cursor.getInt(cursor.getColumnIndex(MyMessage.FROM_SABER)),
                        cursor.getString(cursor.getColumnIndex(MyMessage.MSG))
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return messages;
    }

    public void addMessage(MyMessage myMessage){

        ContentValues contentValues = new ContentValues();
        contentValues.put("msg", myMessage.getMessage());

        // 因为SQLite没有boolean类型所以在存取的时候转换成整数
        contentValues.put("fromSaber", myMessage.isFromSaber()
                ? MyMessage.IS_FROM_SABER : 0);

        database.insert(SQLiteOpener.TALK_LOG_TABLE,
                null, contentValues);
    }

}
