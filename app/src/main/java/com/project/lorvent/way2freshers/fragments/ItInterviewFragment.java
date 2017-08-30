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
import com.project.lorvent.way2freshers.adapters.ItInterviewAdapter;
import com.project.lorvent.way2freshers.models.ItInterview;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItInterviewFragment extends Fragment {

    ArrayList<ItInterview> itInterviewArrayList;
    RecyclerView rv;
    ItInterviewAdapter adapter;
    MaterialSearchView searchView;
     String category_id;
    FrameLayout layout;
    public ItInterviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_it_interview, container, false);;

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);
        searchView = (MaterialSearchView) getActivity().findViewById(R.id.search_view);
        layout = (FrameLayout) v.findViewById(R.id.frame);

        if (actionBar != null) {
            actionBar.setTitle("Select Topic");
        }
        itInterviewArrayList = new ArrayList<>();

        ItInterview interview1=new ItInterview();
        interview1.setCategory_id("3380");
        interview1.setSub_name(".Net");
        ItInterview interview2=new ItInterview();
        interview2.setCategory_id("3320");
        interview2.setSub_name("ASP.NET");
        ItInterview interview3=new ItInterview();
        interview3.setCategory_id("4771");
        interview3.setSub_name("C#");
        ItInterview interview4=new ItInterview();
        interview4.setCategory_id("168");
        interview4.setSub_name("DATA STRUCTURE");
        ItInterview interview5=new ItInterview();
        interview5.setCategory_id("0");
        interview5.setSub_name("FLASH");
        ItInterview interview6=new ItInterview();
        interview6.setCategory_id("3484");
        interview6.setSub_name("LINUX");
        ItInterview interview7=new ItInterview();
        interview7.setCategory_id("158");
        interview7.setSub_name("RDBMS");
        ItInterview interview8=new ItInterview();
        interview8.setCategory_id("753");
        interview8.setSub_name("Testing Tools");
        ItInterview interview9=new ItInterview();
        interview9.setCategory_id("3306");
        interview9.setSub_name("Ajax");
        ItInterview interview10=new ItInterview();
        interview10.setCategory_id("97");
        interview10.setSub_name("C");
        ItInterview interview11=new ItInterview();
        interview11.setCategory_id("3364");
        interview11.setSub_name("CSS");
        ItInterview interview12=new ItInterview();
        interview12.setCategory_id("3431");
        interview12.setSub_name("HTML");
        ItInterview interview13=new ItInterview();
        interview13.setCategory_id("3512");
        interview13.setSub_name("ORACLE");
        ItInterview interview14=new ItInterview();
        interview14.setCategory_id("3500");
        interview14.setSub_name("SAP");
        ItInterview interview15=new ItInterview();
        interview15.setCategory_id("247");
        interview15.setSub_name("UNIX");
        ItInterview interview16=new ItInterview();
        interview16.setCategory_id("417");
        interview16.setSub_name("JAVA");
        ItInterview interview17=new ItInterview();
        interview17.setCategory_id("1134");
        interview17.setSub_name("PHP");
        ItInterview interview18=new ItInterview();
        interview18.setCategory_id("3542");
        interview18.setSub_name("Visual Basic");

        itInterviewArrayList.add(interview1);
        itInterviewArrayList.add(interview2);
        itInterviewArrayList.add(interview3);
        itInterviewArrayList.add(interview4);
        itInterviewArrayList.add(interview5);
        itInterviewArrayList.add(interview6);
        itInterviewArrayList.add(interview7);
        itInterviewArrayList.add(interview8);
        itInterviewArrayList.add(interview9);
        itInterviewArrayList.add(interview10);
        itInterviewArrayList.add(interview11);
        itInterviewArrayList.add(interview12);
        itInterviewArrayList.add(interview13);
        itInterviewArrayList.add(interview14);
        itInterviewArrayList.add(interview15);
        itInterviewArrayList.add(interview16);
        itInterviewArrayList.add(interview17);
        itInterviewArrayList.add(interview18);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ItInterviewAdapter(getActivity(), itInterviewArrayList);
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
                ArrayList<ItInterview> subinterviewArrayList = new ArrayList<>();
                for (int i = 0; i < itInterviewArrayList.size(); i++) {
                    if (StringUtils.containsIgnoreCase(itInterviewArrayList.get(i).getSub_name(),newText)) {
                        subinterviewArrayList.add(itInterviewArrayList.get(i));
                    }
                    //System.out.println("lead item --"+leadsArrayList.get(i).getName()+" "+leadsArrayList.get(i).getNumber());
                }
                rv.setAdapter(new ItInterviewAdapter(getActivity(), subinterviewArrayList));
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
        MyApplication.getInstance().trackScreenView("ItInterviewFragment");

    }
}
