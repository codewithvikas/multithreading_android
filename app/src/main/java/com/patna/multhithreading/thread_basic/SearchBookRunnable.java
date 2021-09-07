package com.patna.multhithreading.thread_basic;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.MainThread;

import com.patna.multhithreading.Constants;
import com.patna.multhithreading.MainActivity;
import com.patna.multhithreading.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class SearchBookRunnable implements Runnable {

    MainActivity activity;
    String query;
    public SearchBookRunnable(Activity activity, String query){
        this.activity = (MainActivity) activity;
        this.query = query;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        HttpURLConnection httpURLConnection = null;
        try {
            String urlString = buildUrl(query);
            Log.i(SearchBookRunnable.class.getSimpleName(),"URL:"+urlString);

            URL url = new URL(urlString);

             httpURLConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();

            String line;
            while ((line = bufferedReader.readLine())!=null){
                    stringBuffer.append(line);
                    activity.runOnUiThread(() -> activity.resultTextView.setText(stringBuffer));
            }

        } catch (IOException e) {
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.resultTextView.setText("There is some issue in Network!!");
                }
            });
        }
        finally {
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
        }
    }

    String buildUrl(String query){
        Uri.Builder builder = new Uri.Builder()
        .scheme("https")
        .authority(Constants.AUTHORITY)
        .path(Constants.URL_PATH)
        .appendQueryParameter("q",query);
            return builder.build().toString();
    }
}
