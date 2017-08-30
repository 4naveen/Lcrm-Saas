package com.project.lorvent.way2freshers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.SchlorshipDetailActivity;
import com.project.lorvent.way2freshers.adapters.LatestJobAdapter;
import com.project.lorvent.way2freshers.utils.AppSession;
import com.project.lorvent.way2freshers.utils.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {
    ArrayList<String> titleArrayList;
    ArrayList<String> urlArrayList;
    TextView answer;
    RecyclerView rv;
    LatestJobAdapter adapter;
    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_recent, container, false);
        setHasOptionsMenu(true);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Dashboard");
        }
        titleArrayList=new ArrayList<>();
        urlArrayList=new ArrayList<>();
        rv = (RecyclerView)v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        adapter = new LatestJobAdapter(getActivity(), AppSession.titleArrayList);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        rv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent i = new Intent(getActivity(), SchlorshipDetailActivity.class);
                i.putExtra("url",AppSession.urlArrayList.get(position));
                getActivity().startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("RecentFragment");
    }
}
