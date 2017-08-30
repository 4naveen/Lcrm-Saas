package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.DetailsActivity;
import com.project.lorvent.way2freshers.models.JobList;
import com.github.siyamed.shapeimageview.BubbleImageView;

import java.util.ArrayList;


public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private Context context;
    private ArrayList<JobList> walkinLists;
    View v;
    public JobAdapter(Context context, ArrayList<JobList> walkinLists) {
        this.context = context;
        this.walkinLists = walkinLists;

    }

    public JobAdapter() {
    }

    public void setJobData(ArrayList<JobList> jobData) {
        walkinLists = jobData;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_latest_jobs, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final JobAdapter.ViewHolder viewHolder, int position) {

        viewHolder.title.setText(walkinLists.get(position).getTitle());
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("url",walkinLists.get(viewHolder.getAdapterPosition()).getUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return walkinLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        BubbleImageView image;
        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.company_name);
        }
    }
}
