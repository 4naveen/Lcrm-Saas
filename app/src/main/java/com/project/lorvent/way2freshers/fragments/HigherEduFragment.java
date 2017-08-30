package com.project.lorvent.way2freshers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.EducationAdapter;
import com.project.lorvent.way2freshers.models.Education;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HigherEduFragment extends Fragment {
    ArrayList<Education> topicArrayList;
    RecyclerView rv;
    EducationAdapter adapter;
    MaterialSearchView searchView;
    String category_id;
    FrameLayout layout;

    public HigherEduFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_higher_edu, container, false);
        layout = (FrameLayout) v.findViewById(R.id.frame);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);
        searchView = (MaterialSearchView) getActivity().findViewById(R.id.search_view);

        if (actionBar != null) {
            actionBar.setTitle("");
        }

        Education education1=new Education();
        education1.setCategory_id("0");
        education1.setName("AIEEE");
        Education education2=new Education();
        education2.setCategory_id("4979");
        education2.setName("EAMCET");
        Education education3=new Education();
        education3.setCategory_id("5029");
        education3.setName("ICET");
        Education education4=new Education();
        education4.setCategory_id("4537");
        education4.setName("LAW");
        Education education5=new Education();
        education5.setCategory_id("0");
        education5.setName("MCA");
        Education education6=new Education();
        education6.setCategory_id("3971");
        education6.setName("PET");
        Education education7=new Education();
        education7.setCategory_id("0");
        education7.setName("SAT");
        Education education8=new Education();
        education8.setCategory_id("8248");
        education8.setName("BITS");
        Education education9=new Education();
        education9.setCategory_id("0");
        education9.setName("GATE");
        Education education10=new Education();
        education10.setCategory_id("0");
        education10.setName("IIT");
        Education education11=new Education();
        education11.setCategory_id("0");
        education11.setName("MAT");
        Education education12=new Education();
        education12.setCategory_id("5257");
        education12.setName("IMCET");
        Education education13=new Education();
        education13.setCategory_id("3972");
        education13.setName("PMPD");
        Education education14=new Education();
        education14.setCategory_id("6586");
        education14.setName("SNAP");
        Education education15=new Education();
        education15.setCategory_id("0");
        education15.setName("CAT");
        Education education16=new Education();
        education16.setCategory_id("0");
        education16.setName("GMAT");
        Education education17=new Education();
        education17.setCategory_id("0");
        education17.setName("JMET");
        Education education18=new Education();
        education18.setCategory_id("0");
        education18.setName("MBA");
        Education education19=new Education();
        education19.setCategory_id("6901");
        education19.setName("NMAT");
        Education education20=new Education();
        education20.setCategory_id("3909");
        education20.setName("PMT");
        Education education21=new Education();
        education21.setCategory_id("5867");
        education21.setName("XAT");
        
        topicArrayList = new ArrayList<>();
        topicArrayList.add(education1);
        topicArrayList.add(education2);
        topicArrayList.add(education3);
        topicArrayList.add(education4);
        topicArrayList.add(education5);
        topicArrayList.add(education6);
        topicArrayList.add(education7);
        topicArrayList.add(education8);
        topicArrayList.add(education9);
        topicArrayList.add(education10);
        topicArrayList.add(education11);
        topicArrayList.add(education12);
        topicArrayList.add(education13);
        topicArrayList.add(education14);
        topicArrayList.add(education15);
        topicArrayList.add(education16);
        topicArrayList.add(education17);
        topicArrayList.add(education18);
        topicArrayList.add(education19);
        topicArrayList.add(education20);
        topicArrayList.add(education21);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new EducationAdapter(getActivity(), topicArrayList);
        rv.setAdapter(adapter);


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
                ArrayList<Education> subtopicArrayList = new ArrayList<>();
                for (int i = 0; i < topicArrayList.size(); i++) {
                    if (StringUtils.containsIgnoreCase(topicArrayList.get(i).getName(),newText)) {
                        subtopicArrayList.add(topicArrayList.get(i));
                    }
                }
                rv.setAdapter(new EducationAdapter(getActivity(), subtopicArrayList));
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public void onResume() {

        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }
        super.onResume();
        MyApplication.getInstance().trackScreenView("HigherEduFragment");

    }
}
