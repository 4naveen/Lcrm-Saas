package com.project.lorvent.way2freshers.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.PlacementPaperAdapter;
import com.project.lorvent.way2freshers.models.PlacementPaper;
import com.project.lorvent.way2freshers.utils.AppConstant;
import com.project.lorvent.way2freshers.utils.NetworkStatus;
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
public class PlacementsFragment extends Fragment {
    ArrayList<PlacementPaper> placementPaperArrayList;
    RecyclerView recyclerView;
    PlacementPaperAdapter mAdapter;
    MaterialSearchView searchView;
    ProgressDialog dialog;
    int no_of_papers;
    FrameLayout layout;

    public PlacementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_placements, container, false);
        if (!NetworkStatus.isConnected(getActivity())) {
            getActivity().finish();
        }
        setHasOptionsMenu(true);
        placementPaperArrayList = new ArrayList<>();
        searchView = (MaterialSearchView) getActivity().findViewById(R.id.search_view);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Placement Papers");
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        layout = (FrameLayout) v.findViewById(R.id.frame);

        new GetAllCompany().execute();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView.setMenuItem(menuItem);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<PlacementPaper> subPlacementPaperList = new ArrayList<>();
                for (int i = 0; i < placementPaperArrayList.size(); i++) {
                    if (StringUtils.containsIgnoreCase(placementPaperArrayList.get(i).getCompany_name(),newText)) {

                        subPlacementPaperList.add(placementPaperArrayList.get(i));
                    }
                }
                recyclerView.setAdapter(new PlacementPaperAdapter(getActivity(), subPlacementPaperList));
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private class GetAllCompany extends AsyncTask<String, Void, String> {
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
                url = new URL(AppConstant.PLACEMENT_PAPEER_URL);
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
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    PlacementPaper paper = new PlacementPaper();
                    paper.setCompany_name(object.getString("name"));
                    paper.setNo_of_ques(String.valueOf(object.getInt("count")));
                    JSONObject object1 = object.getJSONObject("_links");
                    JSONArray array = object1.getJSONArray("wp:post_type");
                    JSONObject object2 = array.getJSONObject(0);
                    paper.setPaper_name_url(object2.getString("href"));
                    placementPaperArrayList.add(paper);
                }
                mAdapter = new PlacementPaperAdapter(getActivity(), placementPaperArrayList);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
                RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
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
