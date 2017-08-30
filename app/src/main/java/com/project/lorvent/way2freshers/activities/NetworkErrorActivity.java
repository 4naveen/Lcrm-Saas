package com.project.lorvent.way2freshers.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.lorvent.way2freshers.MainActivity;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.utils.AppConstant;
import com.project.lorvent.way2freshers.utils.AppSession;
import com.project.lorvent.way2freshers.utils.NetworkStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkErrorActivity extends AppCompatActivity {
    Button cancel, retry;
    ArrayList<String> titleArrayList;
    ArrayList<String> urlArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_error);
        titleArrayList = new ArrayList<>();
        urlArrayList = new ArrayList<>();
        AppSession.titleArrayList = new ArrayList<>();
        AppSession.urlArrayList = new ArrayList<>();
        cancel = (Button) findViewById(R.id.cancel);
        retry = (Button) findViewById(R.id.retry);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkStatus.isConnected(NetworkErrorActivity.this)) {
                    //do nothing
                }
                if (NetworkStatus.isConnected(NetworkErrorActivity.this)) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            new GetAllRecent().execute();
                            Toast.makeText(getApplicationContext(),"Please wait for a second !",Toast.LENGTH_LONG).show();
                        }
                    }, 2000);
                }
            }
        });
    }

    private class GetAllRecent extends AsyncTask<String, Void, String> {
        String response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection connection;
            try {
                url = new URL(AppConstant.RECENT_URL);
                connection = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                }
                response = buffer.toString();
                //  Log.i("response in d",response);
            } catch (IOException e) {

                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    JSONObject object1 = object.getJSONObject("title");
                    titleArrayList.add(object1.getString("rendered"));
                    JSONObject object2 = object.getJSONObject("_links");
                    JSONArray array = object2.getJSONArray("self");
                    JSONObject object3 = array.getJSONObject(0);
                    urlArrayList.add(object3.getString("href"));
                }
                AppSession.titleArrayList.addAll(titleArrayList);
                AppSession.urlArrayList.addAll(urlArrayList);
                Intent i = new Intent(NetworkErrorActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
