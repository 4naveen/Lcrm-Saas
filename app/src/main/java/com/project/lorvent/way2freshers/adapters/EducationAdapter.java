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
import com.project.lorvent.way2freshers.activities.ExamPaperActivity;
import com.project.lorvent.way2freshers.models.Education;

import java.util.ArrayList;

/**
 * Created by User on 2/15/2017.
 */

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Education> topicArrayList;
    private String category_id;
    private ViewGroup viewGroup;

    public EducationAdapter(Context context, ArrayList<Education> topicArrayList) {
        this.context = context;
        this.topicArrayList = topicArrayList;
    }

    @Override
    public EducationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_list_of_skills, parent, false);
        viewGroup=parent;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EducationAdapter.ViewHolder holder, int position) {
        Education interview=topicArrayList.get(position);
       /* if (interview.getCategory_id().equalsIgnoreCase("0"))
        {
            holder.textView.setTextColor(Color.rgb(158,158,158));
        }*/
       holder.textView.setText(interview.getName());
       holder.textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
               imm.hideSoftInputFromWindow(holder.itemView.getWindowToken(), 0);
               Education education= topicArrayList.get(holder.getAdapterPosition());
               category_id =education.getCategory_id();
               if (category_id.equalsIgnoreCase("0") ) {
                   final Snackbar snackbar = Snackbar.make(viewGroup, "Sorry ! No paper available", Snackbar.LENGTH_LONG);
                   View view = snackbar.getView();
                   view.setMinimumWidth(1000);
                   TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                   tv.setTextColor(Color.YELLOW);
                   snackbar.show();
               } else {
                   Intent i = new Intent(context, ExamPaperActivity.class);
                   i.putExtra("id", category_id);
                   context.startActivity(i);
               }
           }
       });

    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.skills);
        }
    }
}
