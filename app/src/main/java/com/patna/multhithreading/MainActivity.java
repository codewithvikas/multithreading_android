package com.patna.multhithreading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.patna.multhithreading.thread_basic.SearchBookRunnable;
import com.patna.multhithreading.handler.SimpleWorker;

public class MainActivity extends AppCompatActivity {

    EditText searchEditText;
    public TextView resultTextView;
    Button searchButton;
    private SimpleWorker simpleWorker;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            resultTextView.setText((String) msg.obj);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchEditText = findViewById(R.id.search_book_et);
        resultTextView = findViewById(R.id.result_tv);
        searchButton = findViewById(R.id.search_bt);

        searchButton.setOnClickListener(view ->{
            String query = searchEditText.getText().toString().trim();

            SearchBookRunnable searchBookRunnable = new SearchBookRunnable(this,query);

            Thread thread = new Thread(searchBookRunnable);
            thread.start();
        });

        simpleWorker = new SimpleWorker();
        simpleWorker.execute(()->{

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 1 Completed";
            handler.sendMessage(message);
        })
        .execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 2 Completed";
            handler.sendMessage(message);
        })
        .execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 3 Completed";
            handler.sendMessage(message);
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleWorker.quit();
    }
}