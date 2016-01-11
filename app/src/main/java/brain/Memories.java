package brain;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import util.CONSTS;

/**
 * Created by Administrator on 2016/1/11 0011.
 */
public class Memories {

    private Context context;

    public Memories(Context context) {
        this.context = context;
    }

    public String getName(){
        SharedPreferences preferences;

        // 为了向下兼容，我使出了谜之表达式，
        // 这个是可以运行的，不用在意报错，保证兼容
        preferences = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                ? context.getSharedPreferences(
                CONSTS.PREFERENCE_NAME,
                Context.MODE_ENABLE_WRITE_AHEAD_LOGGING
        )
                : context.getSharedPreferences(
                CONSTS.PREFERENCE_NAME,
                Context.MODE_WORLD_READABLE
        );

        String text;
        text = preferences.getString(CONSTS.USER_NAME,"") + CONSTS.WHAT_THE_FUCK;

        if(text.equals(CONSTS.HAVEN_T_GOT_NAME + CONSTS.WHAT_THE_FUCK)){
            text = CONSTS.HAVEN_T_GOT_NAME;
        }

        if(!preferences.contains(CONSTS.USER_NAME)){
            text = CONSTS.MEET_FIRST_TIME;

            SharedPreferences.Editor editor;
            editor = preferences.edit();
            editor.putString(CONSTS.USER_NAME, CONSTS.HAVEN_T_GOT_NAME);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                editor.apply();
            }
            else {
                Log.d(this.toString(),"editor.commit() = " + editor.commit());
            }
        }
        return text;
    }
}
