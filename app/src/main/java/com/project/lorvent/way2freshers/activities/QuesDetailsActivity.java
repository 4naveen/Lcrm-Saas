package com.project.lorvent.way2freshers.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuesDetailsActivity extends AppCompatActivity {
String ques_url;
    ProgressDialog dialog;
    TextView title;
    WebView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques_details);
        ques_url=getIntent().getStringExtra("url");
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Questions");
        }
        title = (TextView) findViewById(R.id.title);
        content = (WebView)findViewById(R.id.content);
        new GetQuesDetails().execute();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                finish();

            }
        }
        return super.onOptionsItemSelected(item);
    }

   private class GetQuesDetails extends AsyncTask<String,Void,String>
    {   String response;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(QuesDetailsActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection connection;
            try {
                url = new URL(ques_url);
                connection = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String temp;
                while ((temp=br.readLine())!=null)
                {
                    buffer.append(temp);
                }
                response=buffer.toString();
                //  Log.i("response in d",response);
            } catch (IOException e) {
                MyApplication.getInstance().trackException(e);

                e.printStackTrace();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String response) {
            if (dialog!=null&&dialog.isShowing()){dialog.dismiss();}

            try {
                 JSONObject jsonObject=new JSONObject(response);
                JSONObject object1=jsonObject.getJSONObject("title");
                title.setText(object1.getString("rendered"));
                JSONObject object2=jsonObject.getJSONObject("content");
                content.loadData(object2.get("rendered").toString(),"text/html","UTF-8");


                //rv.setItemAnimator(new DefaultItemAnimator());

            } catch (JSONException e) {
                MyApplication.getInstance().trackException(e);

                e.printStackTrace();
            }
        }}
}
