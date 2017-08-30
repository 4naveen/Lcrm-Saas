package com.project.lorvent.way2freshers.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationJobDetailsFragment extends Fragment {

    String details_url;
    ProgressDialog dialog;
    TextView title;
    WebView content;
    public LocationJobDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_location_job, container, false);
        setHasOptionsMenu(true);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Details");
            actionBar.setDisplayHomeAsUpEnabled(false);

        }
        details_url=getArguments().getString("url");
        title = (TextView)v.findViewById(R.id.title);
        content = (WebView)v.findViewById(R.id.content);
        new GetDetails().execute();

        return v;
    }
    private class GetDetails extends AsyncTask<String,Void,String>
    {   String response;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
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
                url = new URL(details_url);
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
                //Log.i("response--",response);
                JSONObject jsonObject=new JSONObject(response);
                JSONObject object1=jsonObject.getJSONObject("title");
                title.setText(object1.getString("rendered"));
                JSONObject object2=jsonObject.getJSONObject("content");
                content.loadData(object2.get("rendered").toString(),"text/html","UTF-8");
                //rv.setItemAnimator(new DefaultItemAnimator());
            } catch (JSONException e) {
                e.printStackTrace();
                MyApplication.getInstance().trackException(e);

            }
        }}
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("LocJobDetailsFragment");
    }

}
