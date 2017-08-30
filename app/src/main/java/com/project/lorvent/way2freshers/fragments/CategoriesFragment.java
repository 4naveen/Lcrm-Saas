package com.project.lorvent.way2freshers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.CategoryActivity;
import com.project.lorvent.way2freshers.activities.LocationActivity;
import com.project.lorvent.way2freshers.activities.QualificationActivity;
import com.project.lorvent.way2freshers.activities.SkillsActivity;
import com.project.lorvent.way2freshers.utils.NetworkStatus;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

ImageView skills,location,qualification,category;
    public CategoriesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_categories, container, false);
        if (!NetworkStatus.isConnected(getActivity())) {
            getActivity().finish();
        }
        setHasOptionsMenu(true);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Job By Categories");
        }
        skills=(ImageView)v.findViewById(R.id.skills);
        location=(ImageView)v.findViewById(R.id.location);
        qualification=(ImageView)v.findViewById(R.id.qualification);
        category=(ImageView)v.findViewById(R.id.category);
        skills.setOnClickListener(onClickHandler);
        location.setOnClickListener(onClickHandler);
        qualification.setOnClickListener(onClickHandler);
        category.setOnClickListener(onClickHandler);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

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

    private View.OnClickListener onClickHandler = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch(v.getId())
            {

                case R.id.skills:
                {

                    Intent i=new Intent(getActivity(), SkillsActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.location:
                {
                    Intent i=new Intent(getActivity(), LocationActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.qualification:
                {
                    Intent i=new Intent(getActivity(), QualificationActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.category:
                {
                    Intent i=new Intent(getActivity(), CategoryActivity.class);
                    startActivity(i);
                    break;
                }
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("CategoriesFragment");
    }
}
