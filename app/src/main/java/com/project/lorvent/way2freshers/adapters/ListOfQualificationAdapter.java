package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.ListofJobsActivity;
import com.project.lorvent.way2freshers.models.Qualification;

import java.util.ArrayList;

/**
 * Created by User on 2/15/2017.
 */

public class ListOfQualificationAdapter extends RecyclerView.Adapter<ListOfQualificationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Qualification> qualificationArrayList;

    public ListOfQualificationAdapter(Context context, ArrayList<Qualification> qualificationArrayList) {
        this.context = context;
        this.qualificationArrayList = qualificationArrayList;
    }

    @Override
    public ListOfQualificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_list_of_skills, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListOfQualificationAdapter.ViewHolder holder, int position) {
      holder.textView.setText(qualificationArrayList.get(position).getQualification());
holder.textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(context, ListofJobsActivity.class);
        i.putExtra("id",qualificationArrayList.get(holder.getAdapterPosition()).getId());
        i.putExtra("title",qualificationArrayList.get(holder.getAdapterPosition()).getQualification());
        context.startActivity(i);
    }
});

    }

    @Override
    public int getItemCount() {
        return qualificationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.skills);
        }
    }
}
