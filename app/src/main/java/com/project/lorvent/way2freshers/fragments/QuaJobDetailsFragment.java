package com.project.lorvent.way2freshers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuaJobDetailsFragment extends Fragment {


    public QuaJobDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_qua_job_details, container, false);
        setHasOptionsMenu(true);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Peol Technology Private Limited");
            actionBar.setDisplayHomeAsUpEnabled(false);

        }

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("QuajobDetailsFragment");
    }
}
