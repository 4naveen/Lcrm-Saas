package com.project.lorvent.way2freshers.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.ExamPaperActivity;
import com.project.lorvent.way2freshers.adapters.EducationAdapter;
import com.project.lorvent.way2freshers.models.Education;
import com.project.lorvent.way2freshers.utils.RecyclerTouchListener;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class AbroadFragment extends Fragment {

    ArrayList<Education> topicArrayList;
    RecyclerView rv;
    EducationAdapter adapter;
    String category_id;
    FrameLayout layout;
    public AbroadFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_abroad, container, false);;
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);
        layout = (FrameLayout) v.findViewById(R.id.frame);

        if (actionBar != null) {
            actionBar.setTitle("Education");
        }

        Education education1=new Education();
        education1.setCategory_id("0");
        education1.setName("GRE");
        Education education2=new Education();
        education2.setCategory_id("4394");
        education2.setName("IELTS");
        Education education3=new Education();
        education3.setCategory_id("0");
        education3.setName("TOFEL");
        Education education4=new Education();
        education4.setCategory_id("0");
        education4.setName("Study in Australia");
        Education education5=new Education();
        education5.setCategory_id("0");
        education5.setName("Study in Europe");
        Education education6=new Education();
        education6.setCategory_id("0");
        education6.setName("Study in Germany");
        Education education7=new Education();
        education7.setCategory_id("0");
        education7.setName("Study in New Zealand");
        topicArrayList = new ArrayList<>();

        topicArrayList.add(education1);
        topicArrayList.add(education2);
        topicArrayList.add(education3);
        topicArrayList.add(education4);
        topicArrayList.add(education5);
        topicArrayList.add(education6);
        topicArrayList.add(education7);


        rv = (RecyclerView) v.findViewById(R.id.rv);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new EducationAdapter(getActivity(), topicArrayList);
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv, new RecyclerTouchListener.ClickListener() {
            @Override

            public void onClick(View view, int position) {
                Education education= topicArrayList.get(position);
                category_id =education.getCategory_id();
                if (category_id.equalsIgnoreCase("0") ) {
                    final Snackbar snackbar = Snackbar.make(layout, "Sorry ! No any paper available", Snackbar.LENGTH_LONG);
                    View v = snackbar.getView();
                    v.setMinimumWidth(1000);
                    TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.YELLOW);
                    snackbar.show();
                } else {
                    Intent i = new Intent(getActivity(), ExamPaperActivity.class);
                    i.putExtra("id", category_id);
                    getActivity().startActivity(i);
                }
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
        MyApplication.getInstance().trackScreenView("AboardFragment");
    }
}
