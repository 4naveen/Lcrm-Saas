package com.project.lorvent.way2freshers.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.QuesDetailsActivity;
import com.project.lorvent.way2freshers.adapters.ListOfInterviewQuesAdapter;
import com.project.lorvent.way2freshers.utils.AppConstant;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class HrInterviewFragment extends Fragment {

    ArrayList<String> paperArrayList;
    ArrayList<String> urlArrayList;
    String category_id = "95";
    RecyclerView rv;
    ListOfInterviewQuesAdapter adapter;
    ProgressDialog dialog;

    public HrInterviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hr_interview, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rv);
        paperArrayList = new ArrayList<>();
        urlArrayList = new ArrayList<>();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);
        if (actionBar != null) {
            actionBar.setTitle("Select Topic");
        }
        new GetAllInterviewPapers().execute();
        return v;
    }

    private class GetAllInterviewPapers extends AsyncTask<String, Void, String> {
        String response;

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
                url = new URL(AppConstant.INTERVIEW_URL + category_id);
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
                MyApplication.getInstance().trackException(e);

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
                    paperArrayList.add(object1.getString("rendered"));
                    JSONObject object2 = object.getJSONObject("_links");
                    JSONArray array = object2.getJSONArray("self");
                    JSONObject object3 = array.getJSONObject(0);
                    urlArrayList.add(object3.getString("href"));

                    //  Log.i("leadslist--",lead.getName());
                }

                //rv.setItemAnimator(new DefaultItemAnimator());
                RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(lmanager);
                //rv.setItemAnimator(new DefaultItemAnimator());
                // rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

                adapter = new ListOfInterviewQuesAdapter(getActivity(), paperArrayList);
                rv.setAdapter(adapter);
                rv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent i = new Intent(getActivity(), QuesDetailsActivity.class);
                        i.putExtra("url", urlArrayList.get(position));
                        startActivity(i);
                    }
                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            } catch (JSONException e) {
                MyApplication.getInstance().trackException(e);

                e.printStackTrace();
            }
        }
    }
    @Override
    public void onStop() {

        super.onStop();

        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("HrInterviewFragment");
    }
}
