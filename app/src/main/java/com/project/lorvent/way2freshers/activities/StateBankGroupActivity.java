package com.project.lorvent.way2freshers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.adapters.ListOfBankAdapter;
import com.project.lorvent.way2freshers.models.Bank;

import java.util.ArrayList;

public class StateBankGroupActivity extends AppCompatActivity {
    ArrayList<Bank> bankArrayList;
    RecyclerView rv;
    ListOfBankAdapter adapter;
    FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_bank_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = (RecyclerView)findViewById(R.id.rv);
        layout = (FrameLayout)findViewById(R.id.frame);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Select Bank");
        }
        bankArrayList = new ArrayList<>();

        Bank bank1=new Bank();
        Bank bank2=new Bank();
        Bank bank3=new Bank();
        Bank bank4=new Bank();
        Bank bank5=new Bank();
        Bank bank6=new Bank();
        Bank bank7=new Bank();
        Bank bank8=new Bank();
        Bank bank9=new Bank();

        bank1.setId("0");
        bank1.setBank_name("State Bank of India(SBI)");
        bank2.setId("8737");
        bank2.setBank_name("State Bank of Hyderabad(SBH)");
        bank3.setId("8739");
        bank3.setBank_name("State Bank of Bikaner and jaipur");
        bank4.setId("8636");
        bank4.setBank_name("State Bank of Indore");
        bank5.setId("8632");
        bank5.setBank_name("State Bank of Mysore");
        bank6.setId("8628");
        bank6.setBank_name("State Bank of Patiala");
        bank7.setId("0");
        bank7.setBank_name("State Bank of Saurashtra");
        bank8.setId("0");
        bank8.setBank_name("State Bank of Travancore");
        bank9.setId("8624");
        bank9.setBank_name("Tamiland Mercantile bank Ltd");


        bankArrayList.add(bank1);
        bankArrayList.add(bank2);
        bankArrayList.add(bank3);
        bankArrayList.add(bank4);
        bankArrayList.add(bank5);
        bankArrayList.add(bank6);
        bankArrayList.add(bank7);
        bankArrayList.add(bank8);
        bankArrayList.add(bank9);

        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(StateBankGroupActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListOfBankAdapter(StateBankGroupActivity.this, bankArrayList,"State Bank Group");
        rv.setAdapter(adapter);

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
}
