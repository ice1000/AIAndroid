package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import util.BaseActivity;
import util.MyMessage;

/**
 * Created by Administrator on 2016/1/5 0005.
 */
public class SQLiteOpener extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;

    public SQLiteOpener(Context context){
        this(context, BaseActivity.T.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteOpener(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                BaseActivity.T.TALK_LOG_TABLE + "(" +
                MyMessage.ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MyMessage.MSG        + " TEXT, " +
                MyMessage.FROM_SABER + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(this.toString(), BaseActivity.T.WHAT_THE_FUCK);
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
