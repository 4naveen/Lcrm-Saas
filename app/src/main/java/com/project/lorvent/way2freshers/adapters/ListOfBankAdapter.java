package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.BankLoanDetailsActivity;
import com.project.lorvent.way2freshers.models.Bank;

import java.util.ArrayList;

/**
 * Created by User on 2/15/2017.
 */

public class ListOfBankAdapter extends RecyclerView.Adapter<ListOfBankAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Bank> bankArrayList;
    private ViewGroup  viewGroup;
    String bank_type;
    public ListOfBankAdapter(Context context, ArrayList<Bank> bankArrayList,String bank_type) {
        this.context = context;
        this.bankArrayList = bankArrayList;
        this.bank_type=bank_type;
    }

    @Override
    public ListOfBankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_list_of_skills, parent, false);
        viewGroup=parent;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListOfBankAdapter.ViewHolder holder, int position) {
        Bank bank=bankArrayList.get(position);
      /*  if (bank.getId().equalsIgnoreCase("0"))
        {
            holder.textView.setTextColor(Color.rgb(158,158,158));
        }*/
        holder.textView.setText(bank.getBank_name());
        holder.textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
               imm.hideSoftInputFromWindow(holder.itemView.getWindowToken(), 0);
               Bank bank=bankArrayList.get(holder.getAdapterPosition());
               String id=bank.getId();
               if (id.equalsIgnoreCase("0"))
               {
                   final Snackbar snackbar = Snackbar.make(viewGroup, "Sorry ! No details available", Snackbar.LENGTH_LONG);
                   View view = snackbar.getView();
                   view.setMinimumWidth(1000);
                   TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                   tv.setTextColor(Color.YELLOW);
                   snackbar.show();
               }
               else {
                   Intent i=new Intent(context, BankLoanDetailsActivity.class);
                   i.putExtra("bank_id",id);
                   i.putExtra("bank_type",bank_type);
                   i.putExtra("bank_name",bank.getBank_name());
                   context.startActivity(i);
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return bankArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.skills);
        }
    }
}
