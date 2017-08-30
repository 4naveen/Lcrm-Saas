package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.models.Exam;

import java.util.ArrayList;

/**
 * Created by User on 2/15/2017.
 */

public class ListOfExamsAdapter extends RecyclerView.Adapter<ListOfExamsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Exam> examArrayList;

    public ListOfExamsAdapter(Context context, ArrayList<Exam> examArrayList) {
        this.context = context;
        this.examArrayList = examArrayList;
    }

    @Override
    public ListOfExamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_list_of_skills, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListOfExamsAdapter.ViewHolder holder, int position) {
        Exam exam=examArrayList.get(position);
        if (exam.getCategory_id().equalsIgnoreCase("0"))
        {
            holder.textView.setTextColor(Color.rgb(158,158,158));
        }
        holder.textView.setText(exam.getExam_name());
    }

    @Override
    public int getItemCount() {
        return examArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.skills);
        }
    }
}
