package com.project.lorvent.way2freshers.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.project.lorvent.way2freshers.MyApplication;
import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.utils.AppConstant;
import com.github.siyamed.shapeimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BankLoanDetailsActivity extends AppCompatActivity {
    WebView content;
    RoundedImageView imageView;
    TextView bank_name,bank_type;
    String bank_id,bank_text_type,bank_text_name;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_loan_details);
        bank_id=getIntent().getStringExtra("bank_id");
        bank_text_type=getIntent().getStringExtra("bank_type");
        bank_text_name=getIntent().getStringExtra("bank_name");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Bank Loan Details");
        }

        bank_name = (TextView) findViewById(R.id.bank_name);
        bank_type = (TextView) findViewById(R.id.bank_type);
        imageView = (RoundedImageView) findViewById(R.id.image);
        content = (WebView)findViewById(R.id.content);

        bank_name.setText(bank_text_name);
        bank_type.setText(bank_text_type);
        switch (bank_id)
        {
            case "8856":
            {
                imageView.setImageResource(R.drawable.allahabad);
                break;
            }

            case "8860":
            {
                imageView.setImageResource(R.drawable.boi);
                break;
            }

            case "8862":
            {
                imageView.setImageResource(R.drawable.bom);
                break;
            }

            case "8848":
            {
                imageView.setImageResource(R.drawable.idbi);
                break;
            }

            case "8872":
            {
                imageView.setImageResource(R.drawable.oriental);
                break;
            }

            case "8846":
            {
                imageView.setImageResource(R.drawable.pnb);
                break;
            }

            case "8843":
            {
                imageView.setImageResource(R.drawable.syndicate);
                break;
            }

            case "8837":
            {
                imageView.setImageResource(R.drawable.uco);
                break;
            }

            case "8835":
            {
                imageView.setImageResource(R.drawable.axis);
                break;
            }

            case "8831":
            {
                imageView.setImageResource(R.drawable.cub);
                break;
            }

            case "8829":
            {
                imageView.setImageResource(R.drawable.dhanalaxmi);
                break;
            }

            case "8827":
            {
                imageView.setImageResource(R.drawable.fedral);
                break;
            }

            case "8852":
            {
                imageView.setImageResource(R.drawable.hdfc);
                break;
            }
            case "8797":
            {
                imageView.setImageResource(R.drawable.icici);
                break;
            }
            case "8793":
            {
                imageView.setImageResource(R.drawable.jk);
                break;
            }
            case "8791":
            {
                imageView.setImageResource(R.drawable.karur);
                break;
            }
            case "8785":
            {
                imageView.setImageResource(R.drawable.karnataka);
                break;
            }
            case "8789":
            {
                imageView.setImageResource(R.drawable.laxmi);
                break;
            }
            case "8783":
        {
            imageView.setImageResource(R.drawable.saraswat);
            break;
        }
            case "8737":
            {
                imageView.setImageResource(R.drawable.sbh);
                break;
            }
            case "8739":
            {
                imageView.setImageResource(R.drawable.jaipur);
                break;
            }
            case "8636":
            {
                imageView.setImageResource(R.drawable.indore);
                break;
            }
            case "8632":
            {
                imageView.setImageResource(R.drawable.mysore);
                break;
            }
            case "8628":
            {
                imageView.setImageResource(R.drawable.patiala);
                break;
            }
            case "8624":
            {
                imageView.setImageResource(R.drawable.travancore);
                break;
            }

        }
        new GetLoanDetails().execute(bank_id);
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

    private class GetLoanDetails extends AsyncTask<String,Void,String>
    {   String response;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(BankLoanDetailsActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection connection;
            try {
                url = new URL(AppConstant.LOAN_DETAILS_URL+params[0]);
                connection = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String temp;
                while ((temp=br.readLine())!=null)
                {
                    buffer.append(temp);
                }
                response=buffer.toString();
            } catch (IOException e) {
                MyApplication.getInstance().trackException(e);

                e.printStackTrace();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String response) {
            if (dialog!=null&&dialog.isShowing()){dialog.dismiss();}
            try {
               // Log.i("response--",response);
                JSONObject jsonObject=new JSONObject(response);
                JSONObject object1=jsonObject.getJSONObject("content");
                content.loadData(object1.get("rendered").toString(),"text/html","UTF-8");

            } catch (JSONException e) {
                MyApplication.getInstance().trackException(e);

                e.printStackTrace();
            }
        }}
}
