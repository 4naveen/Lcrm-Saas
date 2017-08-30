package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;

import java.util.ArrayList;


public class SchlorshipAdapter extends RecyclerView.Adapter<SchlorshipAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> titleArrayList;

    public SchlorshipAdapter(Context context, ArrayList<String> titleArrayList) {
        this.context = context;
        this.titleArrayList = titleArrayList;
    }

    @Override
    public SchlorshipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_interview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SchlorshipAdapter.ViewHolder holder, int position) {
        holder.textView.setText(titleArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.question);
        }
    }
}
