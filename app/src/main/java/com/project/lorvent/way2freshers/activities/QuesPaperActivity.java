package com.project.lorvent.way2freshers.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.ListOfPapersAdapter;
import com.project.lorvent.way2freshers.utils.RecyclerTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class QuesPaperActivity extends AppCompatActivity {
    ArrayList<String> PaperArrayList;
    ArrayList<String> quesArrayList;
    RecyclerView rv;
    ListOfPapersAdapter adapter;
    ProgressDialog dialog;
    String paper_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques_paper);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Question Paper");
        }
        PaperArrayList= new ArrayList<>();
        quesArrayList=new ArrayList<>();

         paper_url=getIntent().getStringExtra("url");
       // Log.i("url",paper_url);
        rv = (RecyclerView)findViewById(R.id.rv);
        new GetAllPapers().execute();

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

    private class GetAllPapers extends AsyncTask<String,Void,String>
    {   String response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(QuesPaperActivity.this);
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
                url = new URL(paper_url);
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
                JSONArray jsonArray=new JSONArray(response);

                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object=jsonArray.getJSONObject(i);
                    JSONObject object1=object.getJSONObject("title");
                    PaperArrayList.add(object1.getString("rendered"));
                    JSONObject object2 = object.getJSONObject("_links");
                    JSONArray array = object2.getJSONArray("self");
                    JSONObject object3 = array.getJSONObject(0);
                    quesArrayList.add(object3.getString("href"));

                    //  Log.i("leadslist--",lead.getName());
                }

                //rv.setItemAnimator(new DefaultItemAnimator());
                adapter = new ListOfPapersAdapter(QuesPaperActivity.this,PaperArrayList);
                rv.setAdapter(adapter);
                RecyclerView.LayoutManager lmanager = new LinearLayoutManager(QuesPaperActivity.this, LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(lmanager);
                rv.addOnItemTouchListener(new RecyclerTouchListener(QuesPaperActivity.this, rv, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Intent i=new Intent(QuesPaperActivity.this,QuesDetailsActivity.class);
                        i.putExtra("url",quesArrayList.get(position));
                        startActivity(i);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            } catch (JSONException e) {
                MyApplication.getInstance().trackException(e);

                e.printStackTrace();
            }
        }}
}
