package com.project.lorvent.way2freshers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.ListOfQualificationAdapter;
import com.project.lorvent.way2freshers.models.Qualification;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class QualificationActivity extends AppCompatActivity {
    ArrayList<Qualification> qualificationList;
    RecyclerView rv;
    ListOfQualificationAdapter adapter;
    MaterialSearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView=(MaterialSearchView)findViewById(R.id.search_view);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Select Qualification");

        }
        qualificationList = new ArrayList<>();
        Qualification  qualification1=new Qualification();
        qualification1.setId("20");qualification1.setQualification("10+2");
        Qualification  qualification2=new Qualification();
        qualification2.setId("56273");qualification2.setQualification("10th");
        Qualification  qualification3=new Qualification();
        qualification3.setId("5074");qualification3.setQualification("Accounts");
        Qualification  qualification4=new Qualification();
        qualification4.setId("5079");qualification4.setQualification("BA");
        Qualification qualification5=new Qualification();
        qualification5.setId("48916");qualification5.setQualification("B.Arch");
        Qualification  qualification6=new Qualification();
        qualification6.setId("5080");qualification6.setQualification("B.Com");
        Qualification  qualification7=new Qualification();
        qualification7.setId("7692");qualification7.setQualification("B.ed");
        Qualification  qualification8=new Qualification();
        qualification8.setId("5152");qualification8.setQualification("B.Pharmacy");
        Qualification  qualification9=new Qualification();
        qualification9.setId("5153");qualification9.setQualification("B.Sc");

        Qualification  qualification10=new Qualification();
        qualification10.setId("5081");qualification10.setQualification("B.Tech/BE");
        Qualification  qualification11=new Qualification();
        qualification11.setId("21");qualification11.setQualification("BA/MA");
        Qualification  qualification12=new Qualification();
        qualification12.setId("5082");qualification12.setQualification("BCA");
        Qualification  qualification13=new Qualification();
        qualification13.setId("22");qualification13.setQualification("BCom/MCom/ICWA");
        Qualification  qualification14=new Qualification();
        qualification14.setId("5166");qualification14.setQualification("BDS");
        Qualification  qualification15=new Qualification();
        qualification15.setId("5179");qualification15.setQualification("CA");
        Qualification  qualification16=new Qualification();
        qualification16.setId("18762");qualification16.setQualification("Computer Science");
        Qualification  qualification17=new Qualification();
        qualification17.setId("10619");qualification17.setQualification("D-Pharm");

        Qualification  qualification18=new Qualification();
        qualification18.setId("5115");qualification18.setQualification("Degree");
        Qualification  qualification19=new Qualification();
        qualification19.setId("140");qualification19.setQualification("Graduate");
        Qualification  qualification20=new Qualification();
        qualification20.setId("5087");qualification20.setQualification("IT");
        Qualification  qualification21=new Qualification();
        qualification21.setId("3334");qualification21.setQualification("ITI");
        Qualification  qualification22=new Qualification();
        qualification22.setId("14534");qualification22.setQualification("LLB");
        Qualification  qualification23=new Qualification();
        qualification23.setId("11302");qualification23.setQualification("M.Phil");
        Qualification  qualification24=new Qualification();
        qualification24.setId("5083");qualification24.setQualification("M.Com");
        Qualification  qualification25=new Qualification();
        qualification25.setId("5229");qualification25.setQualification("MPharmacy");
        Qualification  qualification26=new Qualification();
        qualification26.setId("399");qualification26.setQualification("MSc");
        Qualification  qualification27=new Qualification();
        qualification27.setId("306");qualification27.setQualification("MTech");
        Qualification  qualification28=new Qualification();
        qualification28.setId("10612");qualification28.setQualification("MA");
        Qualification  qualification29=new Qualification();
        qualification29.setId("18479");qualification29.setQualification("Master Degree");

        Qualification  qualification30=new Qualification();
        qualification30.setId("18267");qualification30.setQualification("Master Degree in Statistics");
        Qualification  qualification31=new Qualification();
        qualification31.setId("98");qualification31.setQualification("MBA");
        Qualification  qualification32=new Qualification();
        qualification32.setId("5084");qualification32.setQualification("MBA/PGDM");
        Qualification  qualification33=new Qualification();
        qualification33.setId("5335");qualification33.setQualification("MBBS");
        Qualification  qualification34=new Qualification();
        qualification34.setId("125");qualification34.setQualification("MCA");
        Qualification  qualification35=new Qualification();
        qualification35.setId("11408");qualification35.setQualification("ME");
        
        Qualification  qualification36=new Qualification();
        qualification36.setId("15386");qualification36.setQualification("MS");
        Qualification  qualification37=new Qualification();
        qualification37.setId("5192");qualification37.setQualification("PG");
        Qualification  qualification38=new Qualification();
        qualification38.setId("419");qualification38.setQualification("PHD");
        Qualification  qualification39=new Qualification();
        qualification39.setId("10618");qualification39.setQualification("SSC");
        Qualification  qualification40=new Qualification();
        qualification40.setId("5191");qualification40.setQualification("UG");
      

        qualificationList.add(qualification1);
        qualificationList.add(qualification2);
        qualificationList.add(qualification3);
        qualificationList.add(qualification4);
        qualificationList.add(qualification5);
        qualificationList.add(qualification6);
        qualificationList.add(qualification7);
        qualificationList.add(qualification8);
        qualificationList.add(qualification9);
        qualificationList.add(qualification10);
        qualificationList.add(qualification11);
        qualificationList.add(qualification12);
        qualificationList.add(qualification13);
        qualificationList.add(qualification14);
        qualificationList.add(qualification15);
        qualificationList.add(qualification16);
        qualificationList.add(qualification17);
        qualificationList.add(qualification18);
        qualificationList.add(qualification19);
        qualificationList.add(qualification20);
        qualificationList.add(qualification21);
        qualificationList.add(qualification22);
        qualificationList.add(qualification23);
        qualificationList.add(qualification24);
        qualificationList.add(qualification25);
        qualificationList.add(qualification26);
        qualificationList.add(qualification27);
        qualificationList.add(qualification28);
        qualificationList.add(qualification29);
        qualificationList.add(qualification30);
        qualificationList.add(qualification31);
        qualificationList.add(qualification32);
        qualificationList.add(qualification33);
        qualificationList.add(qualification34);
        qualificationList.add(qualification35);
        qualificationList.add(qualification36);
        qualificationList.add(qualification37);
        qualificationList.add(qualification38);
        qualificationList.add(qualification39);
        qualificationList.add(qualification40);

        rv = (RecyclerView)findViewById(R.id.rv);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(QualificationActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListOfQualificationAdapter(QualificationActivity.this,qualificationList);
        rv.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        searchView.setMenuItem(menuItem);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Qualification> subQualificationList = new ArrayList<>();
               // Log.i("list data--", String.valueOf(qualificationList.size()));
                for (int i = 0; i < qualificationList.size(); i++) {
                    if (StringUtils.containsIgnoreCase(qualificationList.get(i).getQualification(),newText)) {
                        Qualification  qualification=new Qualification();
                        qualification.setId(qualificationList.get(i).getId());
                        qualification.setQualification(qualificationList.get(i).getQualification());

                        subQualificationList.add(qualification);
                    }
                }
                rv.setAdapter(new ListOfQualificationAdapter(QualificationActivity.this, subQualificationList));
                return false;

            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();

            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {

        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }
        super.onResume();
    }
}
