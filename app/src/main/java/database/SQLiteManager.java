package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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


}
