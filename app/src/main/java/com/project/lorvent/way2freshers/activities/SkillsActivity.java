package com.project.lorvent.way2freshers.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.ListOfSkillsAdapter;
import com.project.lorvent.way2freshers.models.Skills;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class SkillsActivity extends AppCompatActivity {
    ArrayList<Skills> skillsArrayList;
    RecyclerView rv;
    ListOfSkillsAdapter adapter;
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView=(MaterialSearchView)findViewById(R.id.search_view);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Choose your skills");
        }
        skillsArrayList=new ArrayList<>();
        Skills  skill1=new Skills();
        skill1.setId("11390");skill1.setSkills("AJAX");
        Skills  skill2=new Skills();
        skill2.setId("11384");skill2.setSkills("ASP.NET");
        Skills  skill3=new Skills();
        skill3.setId("22985");skill3.setSkills("ASP-SQL");
        Skills  skill4=new Skills();
        skill4.setId("108245");skill4.setSkills("Buisness Development");
        Skills skill5=new Skills();
        skill5.setId("9546");skill5.setSkills("Linux");
        Skills  skill6=new Skills();
        skill6.setId("9547");skill6.setSkills("Unix");
        Skills  skill7=new Skills();
        skill7.setId("19");skill7.setSkills("C/C++");
        Skills  skill8=new Skills();
        skill8.setId("18");skill8.setSkills("C#/.net/ASP/VB");
        Skills  skill9=new Skills();
        skill9.setId("77");skill9.setSkills("CA/ICWA");

        Skills  skill10=new Skills();
        skill10.setId("5061");skill10.setSkills("Communication");
        Skills  skill11=new Skills();
        skill11.setId("5125");skill11.setSkills("Computer");
        Skills  skill12=new Skills();
        skill12.setId("24231");skill12.setSkills("Content Writer");
        Skills  skill13=new Skills();
        //currently crystal report id not working do i change from 11090 to 11386
        skill13.setId("11386");skill13.setSkills("Crystal Reports");
        Skills  skill14=new Skills();
        skill14.setId("18431");skill14.setSkills("CSS");
        Skills  skill15=new Skills();
        skill15.setId("11618");skill15.setSkills("Data Warehousing");
        Skills  skill16=new Skills();
        skill16.setId("24232");skill16.setSkills("Editor");
        Skills  skill17=new Skills();
        skill17.setId("22546");skill17.setSkills("FLASH");

        Skills  skill18=new Skills();
        skill18.setId("319");skill18.setSkills("MS Office");
        Skills  skill19=new Skills();
        skill19.setId("5280");skill19.setSkills("HTML");
        Skills  skill20=new Skills();
        skill20.setId("10370");skill20.setSkills("J2EE");
        Skills  skill21=new Skills();
        skill21.setId("10369");skill21.setSkills("JAVA");
        Skills  skill22=new Skills();
        skill22.setId("11389");skill22.setSkills("Javascript");
        Skills  skill23=new Skills();
        skill23.setId("22718");skill23.setSkills("MySql");
        Skills  skill24=new Skills();
        skill24.setId("5142");skill24.setSkills("Oracle");
        Skills  skill25=new Skills();
        skill25.setId("11089");skill25.setSkills("Sql Server");
        Skills  skill26=new Skills();
        skill26.setId("11387");skill26.setSkills("UML");
        Skills  skill27=new Skills();
        skill27.setId("139");skill27.setSkills("Web Design");
        Skills  skill28=new Skills();
        skill28.setId("11388");skill28.setSkills("Xml/Xslt");
        Skills  skill29=new Skills();
        skill29.setId("10362");skill29.setSkills("PHP");
        Skills  skill30=new Skills();
        skill30.setId("5141");skill30.setSkills("PL/Sql");

        skillsArrayList.add(skill1);
        skillsArrayList.add(skill2);
        skillsArrayList.add(skill3);
        skillsArrayList.add(skill4);
        skillsArrayList.add(skill5);
        skillsArrayList.add(skill6);
        skillsArrayList.add(skill7);
        skillsArrayList.add(skill8);
        skillsArrayList.add(skill9);
        skillsArrayList.add(skill10);
        skillsArrayList.add(skill11);
        skillsArrayList.add(skill12);
        skillsArrayList.add(skill13);
        skillsArrayList.add(skill14);
        skillsArrayList.add(skill15);
        skillsArrayList.add(skill16);
        skillsArrayList.add(skill17);
        skillsArrayList.add(skill18);
        skillsArrayList.add(skill19);
        skillsArrayList.add(skill20);
        skillsArrayList.add(skill21);
        skillsArrayList.add(skill22);
        skillsArrayList.add(skill23);
        skillsArrayList.add(skill24);
        skillsArrayList.add(skill25);
        skillsArrayList.add(skill26);
        skillsArrayList.add(skill27);
        skillsArrayList.add(skill28);
        skillsArrayList.add(skill29);
        skillsArrayList.add(skill30);
        rv = (RecyclerView)findViewById(R.id.rv);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(SkillsActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListOfSkillsAdapter(SkillsActivity.this,skillsArrayList,rv);
        rv.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                if (searchView.isSearchOpen()) {
                searchView.closeSearch();
            }
                finish();

            }
        }
        return super.onOptionsItemSelected(item);
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
                ArrayList<Skills> subLocationList = new ArrayList<>();
                for (int i = 0; i < skillsArrayList.size(); i++) {

                    if (StringUtils.containsIgnoreCase(skillsArrayList.get(i).getSkills(),newText)) {
                        Skills  skill=new Skills();
                        skill.setId(skillsArrayList.get(i).getId());
                        skill.setSkills(skillsArrayList.get(i).getSkills());
                        subLocationList.add(skill);
                    }
                }
                rv.setAdapter(new ListOfSkillsAdapter(SkillsActivity.this, subLocationList,rv));

                return false;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {

        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }
        super.onResume();
    }
}
