package com.project.lorvent.way2freshers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.NationalizedBankActivity;
import com.project.lorvent.way2freshers.activities.PrivateBankActivity;
import com.project.lorvent.way2freshers.activities.StateBankGroupActivity;
import com.project.lorvent.way2freshers.utils.NetworkStatus;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoanFragment extends Fragment implements View.OnClickListener{

    CardView cv1,cv2,cv3;
    public LoanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_loan, container, false);
        if (!NetworkStatus.isConnected(getActivity())) {
            getActivity().finish();
        }
        setHasOptionsMenu(true);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Loan");
        }
        cv1=(CardView)v.findViewById(R.id.cv1);
        cv2=(CardView)v.findViewById(R.id.cv2);
        cv3=(CardView)v.findViewById(R.id.cv3);
        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
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
    public void onClick(View v) {
     switch (v.getId())
     {
         case R.id.cv1:
         {
             MyApplication.getInstance().trackEvent("NationalizedBank", "clicked", "Track event example");

             Intent i=new Intent(getActivity(), NationalizedBankActivity.class);
             getActivity().startActivity(i);

             break;
         }
         case R.id.cv2:
         {             MyApplication.getInstance().trackEvent("PrivateBank", "clicked", "Track event example");

             Intent i=new Intent(getActivity(), PrivateBankActivity.class);
             getActivity().startActivity(i);
             break;
         }
         case R.id.cv3:
         {             MyApplication.getInstance().trackEvent("StateBank", "clicked", "Track event example");

             Intent i=new Intent(getActivity(), StateBankGroupActivity.class);
             getActivity().startActivity(i);
             break;
         }
     }
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("LoanFragment");
    }
}
