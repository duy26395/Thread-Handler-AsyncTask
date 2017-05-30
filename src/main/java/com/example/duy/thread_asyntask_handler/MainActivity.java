package com.example.duy.thread_asyntask_handler;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;



import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Handler handler;
    AtomicBoolean isRunning = new AtomicBoolean(false);
    ProgressBar progressBar;
    ProgressBar pro;
    TextView textView;
    Button buttonT;
    Button buttonA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.id_pro);
        pro = (ProgressBar)findViewById(R.id.progress);
   ///     textView = (TextView) findViewById(R.id.idtext);
        buttonT = (Button) findViewById(R.id.id_thread);
        buttonA = (Button) findViewById(R.id.id_async);
        buttonT.setOnClickListener(this);
        buttonA.setOnClickListener(this);
        sethandlder();
    }
    public void sethandlder(){
        handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                pro.setProgress(msg.arg1);
                buttonT.setText(msg.arg1 + " % ");
            }
        };
    }
    private void startThread(){
        progressBar.setProgress(0);
        isRunning.set(false);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i<=100 && isRunning.get();i++)
                {
                    SystemClock.sleep(50);
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                     handler.sendMessage(msg);
                }
            }
        });
        isRunning.set(true);
        t1.start();
    }
    private void startAsyncTask(){
        AsnycTask asyncTask = new AsnycTask(this);
        asyncTask.execute();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_thread:
                startThread();
                break;
            case R.id.id_async:
                startAsyncTask();
        }
    }

}