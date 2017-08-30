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

public class PrivateBankActivity extends AppCompatActivity {
    ArrayList<Bank> bankArrayList;
    RecyclerView rv;
    ListOfBankAdapter adapter;
    MaterialSearchView searchView;
    FrameLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_bank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = (RecyclerView)findViewById(R.id.rv);
        layout = (FrameLayout)findViewById(R.id.frame);

        searchView=(MaterialSearchView)findViewById(R.id.search_view);
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
        Bank bank20=new Bank();

        bank1.setId("8835");
        bank1.setBank_name("AxisBank");
        bank2.setId("0");
        bank2.setBank_name("Bank of Punjab");
        bank3.setId("0");
        bank3.setBank_name("Bharat Overseas Bank");
        bank4.setId("8860");
        bank4.setBank_name("Bank Of Rajasthan");
        bank5.setId("8862");
        bank5.setBank_name("Catholic Syrian Bank");
        bank6.setId("0");
        bank6.setBank_name("Centurion Bank of Punjab");
        bank7.setId("8831");
        bank7.setBank_name("City Union Bank");
        bank8.setId("8829");
        bank8.setBank_name("DhanLakshmi Bank");
        bank9.setId("8827");
        bank9.setBank_name("Fedral Bank");
        bank10.setId("8852");
        bank10.setBank_name("HDFC Bank");
        bank11.setId("8797");
        bank11.setBank_name("ICICI Bank");
        bank12.setId("0");
        bank12.setBank_name("Ingvysya Bank");
        bank13.setId("8793");
        bank13.setBank_name("Jammu and Kashmir Bank");
        bank14.setId("8791");
        bank14.setBank_name("Karur Vysya Bank");
        bank15.setId("8785");
        bank15.setBank_name("Karnataka  Bank");
        bank16.setId("8789");
        bank16.setBank_name("Lakshmi Villas Bank");
        bank17.setId("0");
        bank17.setBank_name("Ratnakar Bank ");
        bank18.setId("8783");
        bank18.setBank_name("Saraswat Bank");
        bank19.setId("0");
        bank19.setBank_name("South Indian Bank");
        bank20.setId("0");
        bank20.setBank_name("VITJNAN PRADHAN SCHEME");

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
        bankArrayList.add(bank20);

        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(PrivateBankActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lmanager);
        //rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListOfBankAdapter(PrivateBankActivity.this, bankArrayList,"Private bank");
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
                    //System.out.println("lead item --"+leadsArrayList.get(i).getName()+" "+leadsArrayList.get(i).getNumber());
                }
                rv.setAdapter(new ListOfBankAdapter(PrivateBankActivity.this, subbankArrayList,"Private bank"));
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
