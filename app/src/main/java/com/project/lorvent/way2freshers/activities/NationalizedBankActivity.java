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
import com.project.lorvent.way2freshers.adapters.ListOfBankAdapter;
import com.project.lorvent.way2freshers.models.Bank;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class NationalizedBankActivity extends AppCompatActivity {
    ArrayList<Bank> bankArrayList;
    RecyclerView rv;
    ListOfBankAdapter adapter;
    MaterialSearchView searchView;
    FrameLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nationalized_bank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = (RecyclerView)findViewById(R.id.rv);
        searchView=(MaterialSearchView)findViewById(R.id.search_view);
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
        Bank bank10=new Bank();
        Bank bank11=new Bank();
        Bank bank12=new Bank();
        Bank bank13=new Bank();
        Bank bank14=new Bank();
        Bank bank15=new Bank();
        Bank bank16=new Bank();
        Bank bank17=new Bank();
        Bank bank18=new Bank();
        Bank bank19=new Bank();
        bank1.setId("0");
        bank1.setBank_name("Andhra Bank");
        bank2.setId("8856");
        bank2.setBank_name("Allahabad Bank");
        bank3.setId("0");
        bank3.setBank_name("Bank Of Baroda-Baroda Gyan");
        bank4.setId("8860");
        bank4.setBank_name("Bank Of India");
        bank5.setId("8862");
        bank5.setBank_name("Bank Of Maharashtra");
        bank6.setId("0");
        bank6.setBank_name("Canera Bank");
        bank7.setId("0");
        bank7.setBank_name("Central Bank");
        bank8.setId("0");
        bank8.setBank_name("Corporation Bank");
        bank9.setId("0");
        bank9.setBank_name("Dena Bank");
        bank10.setId("0");
        bank10.setBank_name("Indian Bank");
        bank11.setId("0");
        bank11.setBank_name("Indian Overseas Bank");
        bank12.setId("8848");
        bank12.setBank_name("IDBI Bank");
        bank13.setId("8872");
        bank13.setBank_name("Oriental Bank of Commerece");
        bank14.setId("8846");
        bank14.setBank_name("Punjab National Bank");
        bank15.setId("0");
        bank15.setBank_name("Punjab and Sindh Bank");
        bank16.setId("8843");
        bank16.setBank_name("Syndicate Bank");
        bank17.setId("0");
        bank17.setBank_name("Union Bank of India ");
        bank18.setId("8837");
        bank18.setBank_name("UCO Bank");
        bank19.setId("0");
        bank19.setBank_name("Vijaya Bank");

        bankArrayList.add(bank1);
        bankArrayList.add(bank2);
        bankArrayList.add(bank3);
        bankArrayList.add(bank4);
        bankArrayList.add(bank5);
        bankArrayList.add(bank6);
        bankArrayList.add(bank7);
        bankArrayList.add(bank8);
        bankArrayList.add(bank9);
        bankArrayList.add(bank10);
        bankArrayList.add(bank11);
        bankArrayList.add(bank12);
        bankArrayList.add(bank13);
        bankArrayList.add(bank14);
        bankArrayList.add(bank15);
        bankArrayList.add(bank16);
        bankArrayList.add(bank17);
        bankArrayList.add(bank18);
        bankArrayList.add(bank19);

        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(NationalizedBankActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListOfBankAdapter(NationalizedBankActivity.this, bankArrayList,"Nationalized bank");
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

                ArrayList<Bank> subbankArrayList = new ArrayList<>();
                for (int i = 0; i < bankArrayList.size(); i++) {
                    if (StringUtils.containsIgnoreCase(bankArrayList.get(i).getBank_name(),newText)) {
                        subbankArrayList.add(bankArrayList.get(i));
                    }
                }
                rv.setAdapter(new ListOfBankAdapter(NationalizedBankActivity.this, subbankArrayList,"Nationalized bank"));
                return true;
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
