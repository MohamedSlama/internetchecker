package com.mohamedslama.internetchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    new CheckInternet(getApplicationContext(), MainActivity.this)
                            .execute();
                    handler.postDelayed(this, 1000);
                } catch (ExceptionInInitializerError e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void processFinish(Boolean output) {
        if (output) {
            Log.d("TAG", "Internet status is connected");
            getWindow().setStatusBarColor(Color.GREEN);
        } else {
            Log.d("TAG", "Internet status is disconnected");
            getWindow().setStatusBarColor(Color.RED);
        }
    }
}