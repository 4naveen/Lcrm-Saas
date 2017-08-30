package com.project.lorvent.way2freshers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.ListOfLocationAdapter;
import com.project.lorvent.way2freshers.models.Location;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {
    ArrayList<Location> locationArrayList;
    RecyclerView rv;
    ListOfLocationAdapter adapter;
    MaterialSearchView searchView;
    FrameLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Select Location");

        }
        layout = (FrameLayout) findViewById(R.id.activity_skills);


        locationArrayList = new ArrayList<>();
        Location location1 = new Location();
        location1.setId("107729");
        location1.setLocation("Ahmedabad");
        Location location2 = new Location();
        location2.setId("107733");
        location2.setLocation("Bangalore");
        Location location3 = new Location();
        location3.setId("107751");
        location3.setLocation("Delhi");
        Location location4 = new Location();
        location4.setId("60346");
        location4.setLocation("Hyderabad");
        Location location5 = new Location();
        location5.setId("60889");
        location5.setLocation("Chennai");
        Location location6 = new Location();
        location6.setId("107772");
        location6.setLocation("Pune");
        Location location7 = new Location();
        location7.setId("88545");
        location7.setLocation("Kolkata");
        Location location8 = new Location();
        location8.setId("107765");
        location8.setLocation("Mumbai");
        Location location9 = new Location();
        location9.setId("107797");
        location9.setLocation("Noida/NCR");

        Location location10 = new Location();
        location10.setId("60710");
        location10.setLocation("Allahabad");
        Location location11 = new Location();
        location11.setId("108036");
        location11.setLocation("Amritsar");
        Location location12 = new Location();
        location12.setId("60713");
        location12.setLocation("Baroda");
        Location location13 = new Location();
        location13.setId("60716");
        location13.setLocation("Bhopal");
        Location location14 = new Location();
        location14.setId("60715");
        location14.setLocation("Bhubaneshwar");
        Location location15 = new Location();
        location15.setId("107735");
        location15.setLocation("Chandigarh");
        Location location16 = new Location();
        location16.setId("107948");
        location16.setLocation("Cochin");
        Location location17 = new Location();
        location17.setId("60729");
        location17.setLocation("Coimbatore");

        Location location18 = new Location();
        location18.setId("60730");
        location18.setLocation("Dehradun");
        Location location19 = new Location();
        location19.setId("107758");
        location19.setLocation("GurGoan");
        Location location20 = new Location();
        location20.setId("105408");
        location20.setLocation("Guwahati");
        Location location21 = new Location();
        location21.setId("107811");
        location21.setLocation("Indore");
        Location location22 = new Location();
        location22.setId("60601");
        location22.setLocation("Jaipur");
        Location location23 = new Location();
        location23.setId("80725");
        location23.setLocation("Jamshedpur");
        Location location24 = new Location();
        location24.setId("60731");
        location24.setLocation("Kanpur");
        Location location25 = new Location();
        location25.setId("107946");
        location25.setLocation("Kochi");
        Location location26 = new Location();
        location26.setId("107744");
        location26.setLocation("Lucknow");
        Location location27 = new Location();
        location27.setId("60733");
        location27.setLocation("Mysore");
        Location location28 = new Location();
        location28.setId("0");
        location28.setLocation("Nagpur");
        Location location29 = new Location();
        location29.setId("60719");
        location29.setLocation("Patna");

        Location location30 = new Location();
        location30.setId("60721");
        location30.setLocation("Raipur");
        Location location31 = new Location();
        location31.setId("60726");
        location31.setLocation("Ranchi");
        Location location32 = new Location();
        location32.setId("74978");
        location32.setLocation("Surat");
        Location location33 = new Location();
        location33.setId("0");
        location33.setLocation("Tripura");
        Location location34 = new Location();
        location34.setId("0");
        location34.setLocation("Varanasi");
        Location location35 = new Location();
        location35.setId("0");
        location35.setLocation("Vishakapatnam");

        locationArrayList.add(location1);
        locationArrayList.add(location2);
        locationArrayList.add(location3);
        locationArrayList.add(location4);
        locationArrayList.add(location5);
        locationArrayList.add(location6);
        locationArrayList.add(location7);
        locationArrayList.add(location8);
        locationArrayList.add(location9);
        locationArrayList.add(location10);
        locationArrayList.add(location11);
        locationArrayList.add(location12);
        locationArrayList.add(location13);
        locationArrayList.add(location14);
        locationArrayList.add(location15);
        locationArrayList.add(location16);
        locationArrayList.add(location17);
        locationArrayList.add(location18);
        locationArrayList.add(location19);
        locationArrayList.add(location20);
        locationArrayList.add(location21);
        locationArrayList.add(location22);
        locationArrayList.add(location23);
        locationArrayList.add(location24);
        locationArrayList.add(location25);
        locationArrayList.add(location26);
        locationArrayList.add(location27);
        locationArrayList.add(location28);
        locationArrayList.add(location29);
        locationArrayList.add(location30);
        locationArrayList.add(location31);
        locationArrayList.add(location32);
        locationArrayList.add(location33);
        locationArrayList.add(location34);
        locationArrayList.add(location35);

        rv = (RecyclerView) findViewById(R.id.rv);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(LocationActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        rv.setHasFixedSize(true);

        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListOfLocationAdapter(LocationActivity.this, locationArrayList);
        rv.setAdapter(adapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView.setMenuItem(menuItem);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Location> subLocationList = new ArrayList<>();
                for (int i = 0; i < locationArrayList.size(); i++) {
                 /*   String locations= locationArrayList.get(i).getLocation()
                            .replace(locationArrayList.get(i).getLocation().charAt(0),
                                    Character.toLowerCase(locationArrayList.get(i).getLocation().charAt(0)));
                    Log.i("location",locations);*/
                    if (StringUtils.containsIgnoreCase(locationArrayList.get(i).getLocation(), newText)) {
                        Location location = new Location();
                        location.setId(locationArrayList.get(i).getId());
                        location.setLocation(locationArrayList.get(i).getLocation());

                        subLocationList.add(location);
                    }
                }
                rv.setAdapter(new ListOfLocationAdapter(LocationActivity.this, subLocationList));
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
