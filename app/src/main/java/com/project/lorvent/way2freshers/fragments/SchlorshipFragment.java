package com.project.lorvent.way2freshers.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.SchlorshipDetailActivity;
import com.project.lorvent.way2freshers.adapters.SchlorshipAdapter;
import com.project.lorvent.way2freshers.utils.AppConstant;
import com.project.lorvent.way2freshers.utils.NetworkStatus;
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
public class SchlorshipFragment extends Fragment {
    ArrayList<String> titleArrayList;
    ArrayList<String> urlArrayList;
    TextView answer;
    RecyclerView rv;
    SchlorshipAdapter adapter;
    ProgressDialog dialog;

    public SchlorshipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_schlorship, container, false);
        if (!NetworkStatus.isConnected(getActivity())) {
            getActivity().finish();
        }
        setHasOptionsMenu(true);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Scholarships");
        }
        titleArrayList=new ArrayList<>();
        urlArrayList=new ArrayList<>();

        rv = (RecyclerView)v.findViewById(R.id.rv);
       new GetAllSchlorship().execute();
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        NavigationView navigationView=(NavigationView) getActivity().findViewById(R.id.nav_view);
                        navigationView.getMenu().findItem(R.id.dashboard).setChecked(true);
                        Fragment fragment1 = new DashboardFragment();
                        FragmentTransaction trans1 = getFragmentManager().beginTransaction();
                        trans1.replace(R.id.frame, fragment1);
                        trans1.addToBackStack(null);
                        trans1.commit();
                        return true;
                    }
                }
                return false;
            }
        });
        return v;
    }
    private class GetAllSchlorship extends AsyncTask<String, Void, String> {
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
                url = new URL(AppConstant.SCHLORSHIP_DETAILS_URL);
                connection = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                }
                response = buffer.toString();
            } catch (IOException e) {

                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    JSONObject object1=object.getJSONObject("title");
                    titleArrayList.add(object1.getString("rendered"));
                    JSONObject object2 = object.getJSONObject("_links");
                    JSONArray array = object2.getJSONArray("self");
                    JSONObject object3 = array.getJSONObject(0);
                    urlArrayList.add(object3.getString("href"));
                }

                RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(lmanager);

                adapter = new SchlorshipAdapter(getActivity(), titleArrayList);
                rv.setAdapter(adapter);
                rv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                            Intent i = new Intent(getActivity(), SchlorshipDetailActivity.class);
                            i.putExtra("url",urlArrayList.get(position));
                            getActivity().startActivity(i);
                    }
                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));
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
        MyApplication.getInstance().trackScreenView("SchlorshipFragment");
    }
}
