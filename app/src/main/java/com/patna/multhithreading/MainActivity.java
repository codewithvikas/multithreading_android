package com.patna.multhithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.patna.multhithreading.thread_basic.SearchBookRunnable;

public class MainActivity extends AppCompatActivity {

    EditText searchEditText;
    public TextView resultTextView;
    Button searchButton;

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


    }
}