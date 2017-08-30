package com.project.lorvent.way2freshers.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.JobAdapter;
import com.project.lorvent.way2freshers.models.JobList;
import com.project.lorvent.way2freshers.utils.AppConstant;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.commons.lang3.StringUtils;
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
public class WalkinFragment extends Fragment {
    ArrayList<JobList> walkinArrayList;
    RecyclerView recyclerView;
    JobAdapter mAdapter;
    MaterialSearchView searchView;
    ProgressDialog dialog;
    public WalkinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_walkin, container, false);
        setHasOptionsMenu(true);
        walkinArrayList=new ArrayList<>();
        searchView=(MaterialSearchView)getActivity().findViewById(R.id.search_view);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview);
        new GetAllWalkinJob().execute();
        return v;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        searchView.setMenuItem(menuItem);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<JobList> subWalkinArrayList=new ArrayList<>();
                for (int i=0;i<walkinArrayList.size();i++)
                {
                    if (StringUtils.containsIgnoreCase(walkinArrayList.get(i).getTitle(),newText))
                    {

                        subWalkinArrayList.add(walkinArrayList.get(i));
                    }
                    //System.out.println("lead item --"+leadsArrayList.get(i).getName()+" "+leadsArrayList.get(i).getNumber());
                }
                recyclerView.setAdapter(new JobAdapter(getActivity(),subWalkinArrayList));
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


   private class GetAllWalkinJob extends AsyncTask<String, Void, String> {
        String response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());

            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
           // dialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection connection;
            try {
                url = new URL(AppConstant.WALKIN_JOB_URL);
                connection = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                }
                response = buffer.toString();
            } catch (IOException e) {
                MyApplication.getInstance().trackException(e);

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
               // Log.i("response--", response);
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JobList list=new JobList();

                    JSONObject object = jsonArray.getJSONObject(i);
                    JSONObject object1=object.getJSONObject("title");
                    list.setTitle(object1.getString("rendered"));
                    JSONObject object2 = object.getJSONObject("_links");
                    JSONArray array = object2.getJSONArray("self");
                    JSONObject object3 = array.getJSONObject(0);
                        list.setUrl(object3.getString("href"));
                    walkinArrayList.add(list);
                }
                mAdapter=new JobAdapter(getActivity(),walkinArrayList);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
                RecyclerView.LayoutManager lmanager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(lmanager);

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

        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }
        super.onResume();
        MyApplication.getInstance().trackScreenView("WalkinFragment");

    }

}
