package com.example.demo.listview_demo;

import android.app.PictureInPictureParams;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ReFlashListView.IReflashListener {

    ArrayList<ApkEntity> apk_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        showList(apk_list);
    }

    MyAdapter adapter;
    ReFlashListView listView;
    private void showList(ArrayList<ApkEntity> apk_list){
        if (adapter == null){
            listView = (ReFlashListView) findViewById(R.id.listview);
            listView.setInterface(this);
            adapter = new MyAdapter(this,apk_list);
            listView.setAdapter(adapter);
        }else {

            adapter.onDateChange(apk_list);
        }
    }
    private void setData(){
        apk_list = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            ApkEntity entity = new ApkEntity();
            entity.setName("good data");
            entity.setDes("one need say");
            entity.setInfo("50%");
            apk_list.add(entity);

        }
    }

    private void setReflashData(){
        for (int i = 0;i<2;i++){
            ApkEntity entity = new ApkEntity();
            entity.setName("new data");
            entity.setDes("one need say");
            entity.setInfo("50%");
            apk_list.add(0,entity);
        }
    }

    @Override
    public void onReflash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setReflashData();
                showList(apk_list);
                listView.reflashComplete();
            }
        },2000);

    }
}
