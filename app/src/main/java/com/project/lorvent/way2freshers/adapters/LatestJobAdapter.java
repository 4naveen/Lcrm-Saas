package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.github.siyamed.shapeimageview.BubbleImageView;

import java.util.ArrayList;


public class LatestJobAdapter extends RecyclerView.Adapter<LatestJobAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> titleArrayList;
    View v;
    public LatestJobAdapter(Context context, ArrayList<String> titleArrayList) {
        this.context = context;
        this.titleArrayList = titleArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_latest_jobs, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final LatestJobAdapter.ViewHolder viewHolder, int position) {

        viewHolder.company_name.setText(titleArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return titleArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView company_name, position, last_date, location;
        BubbleImageView image;
        ViewHolder(View itemView) {
            super(itemView);
            company_name = (TextView) itemView.findViewById(R.id.company_name);
        }
    }
}
