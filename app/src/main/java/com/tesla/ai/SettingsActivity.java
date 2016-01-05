package com.tesla.ai;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private static final int HDF_STUDIO = 5;
    private static final int PROGRAM_LEAGUE = 6;
    private ListView settings;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        data = new ArrayList<>();
        data.add("推广：HDF Studio群");
        data.add("推广：ProgramLeague群");

        settings = (ListView) findViewById(R.id.settings);

        settings.setAdapter(new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, data));

        settings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        joinQQ(HDF_STUDIO);
                        break;
                    case 1:
                        joinQQ(PROGRAM_LEAGUE);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    public void joinQQ(int QQGroupId){

        String key;

        switch (QQGroupId){
            case PROGRAM_LEAGUE:
                key = "1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et";
                break;
            case HDF_STUDIO:
                key = "BulOWcuBrEZZh2gZDGhxikoGDQaNHlgg";
                break;
            default:
                return;
        }

        if(!joinQQGroup(key)){
            Toast.makeText(SettingsActivity.this,
                    "对不起，您未安装手机QQ，或安装的版本不支持，请更新。",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 发起添加群流程。群号：ProgramLeague(319293196) 的 key 为：
     * 1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et
     * 调用 joinQQGroup(1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et)
     * 即可发起手Q客户端申请加群 ProgramLeague(319293196)
     *
     * 发起添加群流程。群号：HDF Studio ~ 编程之家(196383664) 的 key 为：
     * BulOWcuBrEZZh2gZDGhxikoGDQaNHlgg
     * 调用 joinQQGroup(BulOWcuBrEZZh2gZDGhxikoGDQaNHlgg)
     * 即可发起手Q客户端申请加群 HDF Studio ~ 编程之家(196383664)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回false表示呼起失败
     */
    private boolean joinQQGroup(String key) {

        Intent intent = new Intent();
        intent.setData(Uri.parse(
                "mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com"+
                        "%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，
        // 返回手Q主界面，不设置，按返回会返回到呼起产品界面
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }

}
