package com.example.threadbasic6;

import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    WorkerThread wt;
    Thread wr;
    boolean running = true;
    String TAG = "THREAD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class WorkerThread extends Thread {
        public void run() {
            int i = 0;
            for( i = 0; i < 20 && running; i++ ){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){

                }
                Log.v(TAG, "Thread time = " + i);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        running = true;
        wr = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for ( i = 0; i < 20 && running; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){

                    }
                    Log.v(TAG, "Runnable time = " + i);
                }
            }
        });
        wr.start();

        wt = new WorkerThread();
        wt.start();
        Log.v(TAG, "Now I an in onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        running = false;
        Log.v(TAG, "Now I am in onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Now I am in onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();running = true;
        Log.v(TAG, "Now I am in onResume");
    }

}