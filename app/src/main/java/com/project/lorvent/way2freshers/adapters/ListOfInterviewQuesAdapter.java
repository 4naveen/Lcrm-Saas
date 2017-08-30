package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;

import java.util.ArrayList;


public class ListOfInterviewQuesAdapter extends RecyclerView.Adapter<ListOfInterviewQuesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> paperArrayList;

    public ListOfInterviewQuesAdapter(Context context, ArrayList<String> paperArrayList) {
        this.context = context;
        this.paperArrayList = paperArrayList;
    }

    @Override
    public ListOfInterviewQuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_interview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListOfInterviewQuesAdapter.ViewHolder holder, int position) {
        holder.textView.setText(paperArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return paperArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.question);
        }
    }
}
