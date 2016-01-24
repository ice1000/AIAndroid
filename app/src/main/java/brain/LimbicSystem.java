package brain;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.tesla.ai.R;

import database.SQLiteManager;
import util.BaseActivity;

/**
 * 某种意义上是对于Preference数据库操作的一种封装。
 * Created by Administrator on 2016/1/11 0011.
 */
public class LimbicSystem {

    private SQLiteManager manager;
    private SharedPreferences preferences;
    private Context context;

    public LimbicSystem(Context context) {
        this.context = context;
        manager = new SQLiteManager(context);

        // 为了向下兼容，我使出了谜之表达式，
        // 这个是可以运行的，不用在意报错，保证兼容
        preferences = Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.JELLY_BEAN
                ? context.getSharedPreferences(
                BaseActivity.T.PREFERENCE_NAME,
                Context.MODE_ENABLE_WRITE_AHEAD_LOGGING
        )
                : context.getSharedPreferences(
                BaseActivity.T.PREFERENCE_NAME,
                Context.MODE_WORLD_READABLE
        );
    }

    public void putName(String name){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BaseActivity.T.SABER_NAME, name);

        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.GINGERBREAD)
            editor.apply();
        else
            Log.d(this.toString(),
                    "editor.commit() = " +
                            editor.commit()
            );
    }

    public String getName(){
        return preferences.getString(
                BaseActivity.T.SABER_NAME,
                context.getString(R.string.app_name)
        );
    }

    public String getAppTitle(){

        String text;
        text = preferences.getString(
                BaseActivity.T.SABER_NAME,
                context.getString(R.string.app_name) +
                BaseActivity.T.WHAT_THE_FUCK
        );

        if(text.equals(BaseActivity.T.HAVEN_T_GOT_NAME +
                BaseActivity.T.WHAT_THE_FUCK)){
            text = BaseActivity.T.HAVEN_T_GOT_NAME;
        }

//        if(!preferences.contains(T.SABER_NAME)){
//            text = T.MEET_FIRST_TIME;
//
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString(
//                    T.SABER_NAME,
//                    T.HAVEN_T_GOT_NAME
//            );
//            if (Build.VERSION.SDK_INT >=
//                    Build.VERSION_CODES.GINGERBREAD)
//                editor.apply();
//            else
//                Log.d(this.toString(),
//                        "editor.commit() = " + editor.commit());
//
//        }
        return text;
    }
//     if (Build.VERSION.SDK_INT >=
//                Build.VERSION_CODES.GINGERBREAD)
//            editor.apply();
//        else
//            Log.d(this.toString(),
//                    "editor.commit() = " + editor.commit());
}
