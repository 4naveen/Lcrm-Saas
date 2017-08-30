package com.project.lorvent.way2freshers.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.ExamPaperActivity;
import com.project.lorvent.way2freshers.adapters.ListOfExamsAdapter;
import com.project.lorvent.way2freshers.models.Exam;
import com.project.lorvent.way2freshers.utils.NetworkStatus;
import com.project.lorvent.way2freshers.utils.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamsFragment extends Fragment {
    ArrayList<Exam> examList;
    RecyclerView rv;
    ListOfExamsAdapter adapter;
    static String category_id;
    FrameLayout layout;

    public ExamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_exams, container, false);
        if (!NetworkStatus.isConnected(getActivity())) {
            getActivity().finish();
        }
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);

        if (actionBar != null) {
            actionBar.setTitle("Exams");
        }
        layout = (FrameLayout) v.findViewById(R.id.frame);
        rv = (RecyclerView) v.findViewById(R.id.rv);

        examList= new ArrayList<>();
        Exam exam1=new Exam();
        exam1.setCategory_id("2885");
        exam1.setExam_name("Andhra Bank");
        Exam exam2=new Exam();
        exam2.setCategory_id("12797");
        exam2.setExam_name("Bsnl JTO");
        Exam exam3=new Exam();
        exam3.setCategory_id("3203");
        exam3.setExam_name("CDS");
        Exam exam4=new Exam();
        exam4.setCategory_id("3066");
        exam4.setExam_name("RRB");
        Exam exam5=new Exam();
        exam5.setCategory_id("4637");
        exam5.setExam_name("IAS");
        Exam exam6=new Exam();
        exam6.setCategory_id("0");
        exam6.setExam_name("Fashion Designing");
        Exam exam7=new Exam();
        exam7.setCategory_id("8009");
        exam7.setExam_name("APPSC");
        Exam exam8=new Exam();
        exam8.setCategory_id("3087");
        exam8.setExam_name("Civil Services");
        Exam exam9=new Exam();
        exam9.setCategory_id("3237");
        exam9.setExam_name("NDA");
        Exam exam10=new Exam();
        exam10.setCategory_id("8714");
        exam10.setExam_name("UPSC");
        Exam exam11=new Exam();
        exam11.setCategory_id("1090");
        exam11.setExam_name("SBI");
        Exam exam12=new Exam();
        exam12.setCategory_id("52246");
        exam12.setExam_name("Medical Entrance");

        examList.add(exam1);
        examList.add(exam2);
        examList.add(exam3);
        examList.add(exam4);
        examList.add(exam5);
        examList.add(exam6);
        examList.add(exam7);
        examList.add(exam8);
        examList.add(exam9);
        examList.add(exam10);
        examList.add(exam11);
        examList.add(exam12);

        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListOfExamsAdapter(getActivity(),examList);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        rv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv, new RecyclerTouchListener.ClickListener() {
            @Override

            public void onClick(View view, int position) {
                Exam exam= examList.get(position);
                category_id =exam.getCategory_id();
                if (category_id.equalsIgnoreCase("0") ) {
                    final Snackbar snackbar = Snackbar.make(layout, "Sorry ! No paper available", Snackbar.LENGTH_LONG);
                    View v = snackbar.getView();
                    v.setMinimumWidth(1000);
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.YELLOW);
                    snackbar.show();
                }else {
                    Intent i=new Intent(getActivity(), ExamPaperActivity.class);
                    i.putExtra("id",category_id);
                    getActivity().startActivity(i);


                }
                }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                getActivity().finish();

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("ExamsFragment");
    }
}
