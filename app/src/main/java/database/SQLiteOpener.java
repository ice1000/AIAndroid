package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/1/5 0005.
 */
public class SQLiteOpener extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ice1000.db";
    public static final String TALK_LOG_TABLE = "TALK_LOG_TABLE";

    public static final int DATABASE_VERSION = 1;

    public SQLiteOpener(Context context){
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteOpener(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TALK_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
