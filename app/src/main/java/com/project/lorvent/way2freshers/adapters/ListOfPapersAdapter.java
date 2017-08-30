package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;

import java.util.ArrayList;


public class ListOfPapersAdapter extends RecyclerView.Adapter<ListOfPapersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> skillArrayList;

    public ListOfPapersAdapter(Context context, ArrayList<String> skillArrayList) {
        this.context = context;
        this.skillArrayList = skillArrayList;
    }

    @Override
    public ListOfPapersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_latest_jobs, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListOfPapersAdapter.ViewHolder holder, int position) {
        holder.textView.setText(skillArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return skillArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.company_name);
        }
    }
}
