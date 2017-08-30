package com.project.lorvent.way2freshers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.ListOfCategoryAdapter;
import com.project.lorvent.way2freshers.models.Category;
import com.project.lorvent.way2freshers.utils.RecyclerTouchListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    ArrayList<Category> categoryArrayList;
    RecyclerView rv;
    ListOfCategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Choose your Category");
        }
        categoryArrayList = new ArrayList<>();
        rv = (RecyclerView)findViewById(R.id.rv);
        Category category1=new Category();
        category1.setId("10896");category1.setCategory_name("Accounting/Finance");
        Category category2=new Category();
        category2.setId("171");category2.setCategory_name("Banking");
        Category category3=new Category();
        category3.setId("13");category3.setCategory_name("BPO/KPO");
        Category category4=new Category();
        category4.setId("1706");category4.setCategory_name("Engineering");
        Category category5=new Category();
        category5.setId("307");category5.setCategory_name("Faculty");
        Category category6=new Category();
        category6.setId("755");category6.setCategory_name("Medical");
        Category category7=new Category();
        category7.setId("70560");category7.setCategory_name("Defence");

        categoryArrayList.add(category1);
        categoryArrayList.add(category2);
        categoryArrayList.add(category3);
        categoryArrayList.add(category4);
        categoryArrayList.add(category5);
        categoryArrayList.add(category6);
        categoryArrayList.add(category7);

        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListOfCategoryAdapter(CategoryActivity.this,categoryArrayList);
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(new RecyclerTouchListener(CategoryActivity.this, rv, new RecyclerTouchListener.ClickListener() {
            @Override

            public void onClick(View view, int position) {

                Intent i = new Intent(CategoryActivity.this, ListofJobsActivity.class);
                i.putExtra("id",categoryArrayList.get(position).getId());
                i.putExtra("title",categoryArrayList.get(position).getCategory_name());
                startActivity(i);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                finish();

            }
        }
        return super.onOptionsItemSelected(item);
    }

}
