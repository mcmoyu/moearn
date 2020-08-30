package com.moyu.moearn;

import androidx.appcompat.app.AppCompatActivity;

import androidx.multidex.MultiDex;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.soulgame.sgsdk.tgsdklib.TGSDK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String APP_ID = "5gN8dE1Pt5yWA5qs26k2";
    final String SCENE_ID = "uMTaopx92jiYiyUs69N";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiDex.install(this);

        TGSDK.initialize(this, APP_ID, null); // 初始化

        TGSDK.setDebugModel(true); // 开启调试模式

        Button btn_preload = findViewById(R.id.btn_preload);
        Button btn_show = findViewById(R.id.btn_show);
        Button btn_test = findViewById(R.id.btn_test);

        btn_preload.setOnClickListener(this);
        btn_show.setOnClickListener(this);
        btn_test.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TGSDK.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        TGSDK.onStop(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TGSDK.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TGSDK.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TGSDK.onDestroy(this);
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        TGSDK.onActivityResult(this, reqCode, resCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TGSDK.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_preload:
                new Thread(){
                    @Override
                    public void run() {
                        TGSDK.preloadAd(MainActivity.this);
                    }
                }.start();
                break;
            case R.id.btn_show:
                if(TGSDK.couldShowAd(SCENE_ID)) {
                    TGSDK.showAd(this, SCENE_ID);
                }
                break;
            case R.id.btn_test:
                if(TGSDK.couldShowAd(SCENE_ID)) {
                    TGSDK.showTestView(this, SCENE_ID);
                }
                break;
        }
    }
}
